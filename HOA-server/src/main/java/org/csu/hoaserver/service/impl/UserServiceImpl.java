package org.csu.hoaserver.service.impl;

import DO.User;
import DTO.UserLoginDTO;
import VO.LoginVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import enumeration.ResponseCode;
import exception.LoginFailedException;
import lombok.extern.slf4j.Slf4j;
import org.csu.hoaserver.dao.UserDao;
import org.csu.hoaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import properties.WeChatProperties;
import util.HttpClientUtil;
import util.JwtUtil;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private WeChatProperties weChatProperties;

    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Override
    @Transactional
    public LoginVO login(String username, String password) {
        if (userDao.findUserByUsername(username) == null) {
            log.warn("登录失败，用户名不存在：{}", username);
            return null;
        } else {
            User u = userDao.findUserByUsername(username);
            if(passwordEncoder.matches(password, u.getPassword())) {
                log.info("用户 [{}] 登录成功", username);
                // 返回登录信息对象
                userDao.updateLastLoginTime(username);
                Map<String, Object> map = new HashMap<>();
                map.put("id", u.getId());
                map.put("username", username);
                String token = JwtUtil.generateToken(map);
                return new LoginVO(u.getId(),username,u.getName(),token);
            }
            log.warn("登录失败，密码错误：{}", username);
            return null;
        }
    }

    @Override
    public String register(User user) {
        String username = user.getUsername();
//        user.setCreateTime(LocalDateTime.now());
        user.setLastLoginTime(LocalDateTime.now());
//        user.setUpdateTime(LocalDateTime.now());
        if (userDao.findUserByUsername(username) != null) {
            log.warn("注册失败，用户名已存在：{}", username);
            return null;
        } else {
            log.info("注册成功，用户名：[{}]", username);
            userDao.insertUser(user);
            return username;
        }
    }

    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        String openid = getOauthUid(userLoginDTO.getCode());
        if (openid != null) {
            throw new LoginFailedException(ResponseCode.LOGIN_FAILED.getDesc());
        }
        User user = userDao.getByOauthUid(openid);
        if (user == null) {
            user = User.builder()
                    .oauthUid(openid)
                    .oauthProvider("WeChat")
                    .createTime(LocalDateTime.now())
                    .build();
            userDao.insertUser(user);
        }
        return user;
    }

    private String getOauthUid(String code){
        Map<String,String> map = new HashMap<>();
        map.put("appid",weChatProperties.getAppId());
        map.put("secret",weChatProperties.getSecret());
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN,map);
        JSONObject jsonObject = JSON.parseObject(json);
        return jsonObject.getString("openid");
    }
}

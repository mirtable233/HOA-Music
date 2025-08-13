package org.csu.hoaserver.service.impl;

import DO.User;
import VO.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.csu.hoaserver.dao.UserDao;
import org.csu.hoaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public LoginVO login(User user) {
        String username = user.getUsername();

        if (userDao.findUserByUsername(username) == null) {
            log.warn("登录失败，用户名不存在：{}", username);
            return null;
        } else {
            log.info("用户 [{}] 登录成功", username);
            // 返回登录信息对象
            Map<String, Object> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("username", username);
            JwtUtil.generateToken(map);

            return new LoginVO(user.getId(),username,user.getName);
        }
    }
}

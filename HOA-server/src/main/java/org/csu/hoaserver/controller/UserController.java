package org.csu.hoaserver.controller;


import DO.User;
import DTO.UserLoginDTO;
import VO.LoginVO;
import VO.UserLoginVO;
import constant.JwtClaimsConstant;
import enumeration.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.csu.hoaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import properties.JwtProperties;
import response.CommonResponse;
import util.JwtUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    JwtProperties jwtProperties;


    @PostMapping("/register")
    public CommonResponse<String> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String res = userService.register(user);
        if(res==null||res.isEmpty()) {
         return CommonResponse.createForError(ResponseCode.ACCOUNT_ALREADY_EXIST_OR_EMPTY);
        }
        return CommonResponse.createForSuccess("注册成功");
    }

    @PostMapping("/login")
    public CommonResponse<LoginVO> login(String username, String password) {
        System.out.println(username);
        LoginVO loginVO = userService.login(username,password);
        if (loginVO == null) {
            return CommonResponse.createForError(ResponseCode.WRONG_PASSWORD_OR_USERNAME);
        }
        return CommonResponse.createForSuccess(loginVO);
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("===========================================================");
        List<Integer> list =new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.stream().filter(s ->s.equals(2)).forEach(System.out::println);
        return "success";
    }

    @PostMapping("/user/login")
    public CommonResponse<UserLoginVO> userLogin(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("wechat user login:[{}]", userLoginDTO);
        User user = userService.wxLogin(userLoginDTO);
        Map<String,Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(),jwtProperties.getUserTtl(),
                claims);
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOauthUid())
                .token(token)
                .build();
        return CommonResponse.createForSuccess(userLoginVO);
    }
}

package org.csu.hoaserver.controller;


import DO.User;
import VO.LoginVO;
import enumeration.ResponseCode;
import org.csu.hoaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import response.CommonResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
}

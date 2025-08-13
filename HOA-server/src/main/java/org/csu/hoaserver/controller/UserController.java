package org.csu.hoaserver.controller;


import DO.User;
import VO.LoginVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import response.CommonResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public CommonResponse<LoginVO> login(@RequestBody User user) {

        return CommonResponse.createForError("用户已存在");
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

package com.example.ebookserver.controller;

import com.example.ebookserver.pojo.Result;
import com.example.ebookserver.pojo.User;
import com.example.ebookserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserLoginController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result user_login(@RequestBody User user){
        log.info("用户登录功能");
        User u = userService.userLogin(user);
        return u != null ? Result.success():Result.error("登录失败");
    }
}

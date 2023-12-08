package com.example.ebookserver.controller;

import com.example.ebookserver.pojo.LoginData;
import com.example.ebookserver.pojo.Result;
import com.example.ebookserver.pojo.User;
import com.example.ebookserver.service.UserService;
import com.example.ebookserver.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserLoginController {
    @Autowired
    private UserService userService;
    @PostMapping("/login/user")
    public Result user_login(@RequestBody User user){
        log.info("用户登录功能");
        User u = userService.userLogin(user);
        if (u != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",u.getId());
            claims.put("name",u.getUserName());
            claims.put("email",u.getEmail());
            String jwt = JwtUtils.generateJwt(claims);
            LoginData loginData = new LoginData(jwt,u.getId(),u.getEmail());
            return Result.success(loginData);
        }
        return Result.error("账号或者密码有问题，登录失败了哦");
    }
    @GetMapping("/login/hello")
    public Result hello(){
        return Result.success("hello~I am in login");
    }
}

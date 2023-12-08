package com.example.ebookserver.controller;

import com.example.ebookserver.pojo.LoginData;
import com.example.ebookserver.pojo.Result;
import com.example.ebookserver.pojo.User;
import com.example.ebookserver.service.EmailService;
import com.example.ebookserver.service.UserService;
import com.example.ebookserver.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin
public class UserLoginController {
    @Autowired
    private UserService userService;
    private EmailService emailService;
    @Autowired
    public UserLoginController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/api/v1/login/users")
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
    @PostMapping("/api/v1/login")
    public Result register(@RequestBody User user) {
        log.info("实现账号注册功能");
        int result = userService.register(user);
        if (result == 1) {
            return Result.success("注册成功");
        } else {
            return Result.error("该邮箱已绑定账号");
        }
    }
    @GetMapping("/api/v1/login/hello")
    public Result hello_vi(){
        String from = "2529949859@qq.com"; // 发件人邮箱
        String to = "jiongxnext0809@gmail.com"; // 收件人邮箱
        String subject = "Test Email"; // 邮件主题
        String text = "This is a test email!"; // 邮件内容

        emailService.send(from, to, subject, text);
        return Result.success("hello~I am in api/v1/login");
    }

}

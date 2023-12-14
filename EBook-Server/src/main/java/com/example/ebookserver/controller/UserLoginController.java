package com.example.ebookserver.controller;

import com.example.ebookserver.pojo.*;
import com.example.ebookserver.service.EmailService;
import com.example.ebookserver.service.UserService;
import com.example.ebookserver.utils.RandomPasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/v1")
public class UserLoginController {
    @Autowired
    private UserService userService;
    private EmailService emailService;
    private String code;
    @Autowired
    public UserLoginController(EmailService emailService) {
        this.emailService = emailService;
    }

    /*
    * 用户登录功能实现
    * 获取参数：json{id/email,password}
    * 返回参数：json{code,msg,data{token,name}}
    * */
    @PostMapping("/login/users")
    public Result user_login(@RequestBody User user){
        log.info("用户登录功能");
        LoginData loginData = userService.userLogin(user);
        if (loginData.getCode() == 1){
            return Result.error("账户不存在，请检查是否输入正确");
        } else if (loginData.getCode() == 5) {
            return Result.error("很抱歉，您已被禁止登录");
        }else if (loginData.getCode() == 2){
            return Result.error("密码错误，请重新输入");
        } else{
            return Result.success(loginData);
        }
    }

    /*
     * 用户注册获取邮箱验证码实现
     * 获取参数：email
     * 返回参数：json{code,msg,data}
     * */
    @GetMapping("/login/email-verification")
    public Result hello_vi(@RequestParam String email){
        boolean verifyResult = userService.verifyEmail(email);
        if (verifyResult){
            String from = "2529949859@qq.com"; // 发件人邮箱
            String to = email; // 收件人邮箱
            String subject = "Verification code"; // 邮件主题：验证码
            this.code = RandomPasswordGenerator.generatePassword(6); // 邮件内容 :随机生成6位验证码
            emailService.send(from, to, subject, code);
            return Result.success();
        }
        return Result.error("该邮箱已绑定用户，请更改邮箱");
    }
    /*
    * 验证码核对
    * */
    @PostMapping("/login/email-verification")
    public Result VerCode(@RequestParam String verfityCode){
        log.info("验证码核对");
        if(verfityCode.equals(this.code)){
            this.code=null;
            return Result.success();
        }else return Result.error("验证码填写错误");
    }
    /*
     * 用户注册功能实现
     * 获取参数：json{user{email,name，password,avatar},verCode}
     * 返回参数：json{code,msg,data}
     * */
    @PostMapping("/users")
    public Result register(@RequestBody User user) {
        log.info("实现账号注册功能");
        int result = userService.register(user);
        if (result == 1){
            return Result.success("注册成功");
        }else {
            return Result.error("注册失败");
        }
    }
}

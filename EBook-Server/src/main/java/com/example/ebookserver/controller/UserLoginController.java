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
public class UserLoginController {
    @Autowired
    private UserService userService;
    private EmailService emailService;
    @Autowired
    public UserLoginController(EmailService emailService) {
        this.emailService = emailService;
    }

    /*
    * 用户登录功能实现
    * 获取参数：json{id/email,password}
    * 返回参数：json{code,msg,data{token,name}}
    * */
    @PostMapping("/api/v1/login/users")
    public Result user_login(@RequestBody User user){
        log.info("用户登录功能");
        LoginData loginData = userService.userLogin(user);
        if (loginData.getCode() == 1){
            return Result.error("账户不存在，请检查是否输入正确");
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
    @GetMapping("/api/v1/login/{email}")
    public Result hello_vi(@PathVariable String email){
        boolean verifyResult = userService.verifyEmail(email);
        if (verifyResult){
            String from = "2529949859@qq.com"; // 发件人邮箱
            String to = email; // 收件人邮箱
            String subject = "Verification code"; // 邮件主题：验证码
            String text = RandomPasswordGenerator.generatePassword(6); // 邮件内容 :随机生成6位验证码
            emailService.send(from, to, subject, text);
            return Result.success("邮箱验证成功，验证码为：" + text);
        }
        return Result.error("该邮箱已绑定用户，请更改邮箱");
    }
    /*
     * 用户注册功能实现
     * 获取参数：json{user{email,name，password,avatar},verCode}
     * 返回参数：json{code,msg,data}
     * */
    @PostMapping("/api/v1/login")
    public Result register(@RequestBody RegisterData registerData) {
        log.info("实现账号注册功能");
        int result = userService.register(registerData.getUser());
        if (result == 1){
            return Result.success("注册成功");
        }
        return Result.error("注册失败，请查看信息是否填写有误");
    }
    /*
     * 用户注销功能实现
     * 获取参数：json{password}
     * 返回参数：json{code,msg,data}
     * */
    @DeleteMapping("/api/v1/delete/user")
    public Result user_delete(@RequestBody User user){
        log.info("实现用户注销功能");
        boolean result = userService.delete(user.getId(),user.getPassword());
        if (result){
            return Result.success("用户注销成功");
        }
        return Result.error("密码输入错误，删除失败了");
    }
    @PostMapping("/api/v1/blackList")
    public Result user_blackList(@RequestBody BlackList blackList){
        log.info("实现用户拉黑功能");
        int result = userService.toBlackList(blackList);
        if (result == 1){
            return Result.success("拉黑成功");
        }
        return Result.error("拉黑失败，请确保id有效");
    }
}
package com.example.ebookserver.controller;

import com.example.ebookserver.pojo.*;
import com.example.ebookserver.service.AdminService;
import com.example.ebookserver.service.EmailService;
import com.example.ebookserver.service.UserService;
import com.example.ebookserver.utils.RandomPasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/v1/login")
public class AdminLoginController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/admins")
    public Result admin_login(@RequestBody Admin admin) {
        log.info("管理员登录功能");
        LoginData loginData = adminService.adminLogin(admin);
        if (loginData.getCode() == 1) {
            return Result.error("账户不存在，请检查是否输入正确");
        } else if (loginData.getCode() == 2) {
            return Result.error("密码错误，请重新输入");
        } else {
            return Result.success(loginData);
        }
    }
}

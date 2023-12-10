package com.example.ebookserver.controller;

/**
 * @author wzb
 */
import com.example.ebookserver.pojo.Admin;
import com.example.ebookserver.pojo.Result;
import com.example.ebookserver.service.AdminService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/admins")
@Slf4j
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping()
    public Result createAdmin(@RequestBody Admin admin) {
        log.info("管理员创建功能");

        Admin createdAdmin = adminService.createAdmin(admin);

        if (createdAdmin != null) {
            return Result.success(createdAdmin);
        }
        return Result.error("管理员创建失败");
    }
}
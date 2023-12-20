package com.example.ebookserver.controller;

/**
 * @author wzb
 */
import com.example.ebookserver.pojo.Admin;
import com.example.ebookserver.pojo.Result;
import com.example.ebookserver.pojo.User;
import com.example.ebookserver.service.AdminService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;


    @DeleteMapping("/delete/admin")
    public Result admin_delete(@RequestBody Admin admin){
        log.info("实现管理员删除功能");
        boolean result = adminService.updateRole(admin.getId(), 100);
        if (result){
            return Result.success();
        }
        return Result.error("id输入错误，删除失败了");
    }

    @PostMapping("/admins")
    public Result createAdmin(@RequestBody AdminCreationDTO adminCreationDTO) {
        log.info("管理员创建功能");
        Admin admin = adminCreationDTO.getAdmin();
        Integer adminId = adminCreationDTO.getAdminId(); // Assuming this is the ID of the current admin

        Admin createdAdmin = adminService.createAdmin(admin, adminId);

        if (createdAdmin != null) {
            return Result.success(createdAdmin);
        }
        return Result.error("管理员创建失败");
    }
}
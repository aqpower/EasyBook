package com.example.ebookserver.service.manage;

import com.example.ebookserver.mapper.AdminMapper;
import com.example.ebookserver.pojo.Admin;
import com.example.ebookserver.service.AdminService;
import com.example.ebookserver.utils.MD5Util;
import jdk.jpackage.internal.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin adminLogin(Admin admin) {
        String password = admin.getPassword();

        if(admin.getId() != null) {
            Integer id = admin.getId();
            return adminMapper.getById(id, password);
        } else if (admin.getName() != null) {
            String username = admin.getName();
            return adminMapper.getByUsername(username, password);
        } else {
            return null;
        }
    }
    @Override
    public Admin createAdmin(Admin admin) {
        short role = 2;
        admin.setRole(role);
        //TODO:自定义密码
        String password = this.generatePassword();

        admin.setPassword(password);
        adminMapper.insertAdmin(admin.getName(), MD5Util.encode(password), (short) 2);
        return admin;
    }

    private String generatePassword() {
        Random random = new Random();
        int randomNumber = random.nextInt(90000000) + 10000000;  // 生成 10000000 到 99999999 的随机数
        return Integer.toString(randomNumber);
    }
}

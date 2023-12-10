package com.example.ebookserver.service.manage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ebookserver.mapper.AdminMapper;
import com.example.ebookserver.pojo.Admin;
import com.example.ebookserver.pojo.LoginData;
import com.example.ebookserver.service.AdminService;
import com.example.ebookserver.utils.JwtUtils;
import com.example.ebookserver.utils.MD5Util;
import jdk.jpackage.internal.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public LoginData adminLogin(Admin admin) {
        Map<String, Object> claims = new HashMap<>();
        LoginData loginData = new LoginData();
        String password = admin.getPassword();
        System.out.println(password);
        claims.put("id",admin.getId());
        claims.put("name",admin.getName());
        loginData.setId(admin.getId());
        String jwt = JwtUtils.generateJwt(claims);
        loginData.setToken(jwt);

        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getId, admin.getId());
        Admin one = adminMapper.selectOne(wrapper);
        if (one == null) {
            loginData.setCode(1);
            return loginData;
        }
       loginData.setName(one.getName());
        if (!MD5Util.encode(admin.getPassword()).equals(one.getPassword())) {
            loginData.setCode(2);
            return loginData;
        }
        loginData.setCode(3);
        return loginData;
    }
    @Override
    public Admin createAdmin(Admin admin) {
        short role = 2;
        admin.setRole(role);
        //TODO:自定义密码
        String password = this.generatePassword();
//        password = MD5Util.encode(password);
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

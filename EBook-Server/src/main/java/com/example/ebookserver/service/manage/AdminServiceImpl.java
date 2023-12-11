package com.example.ebookserver.service.manage;

import com.example.ebookserver.mapper.AdminMapper;
import com.example.ebookserver.pojo.Admin;
import com.example.ebookserver.pojo.LoginData;
import com.example.ebookserver.service.AdminService;
import com.example.ebookserver.utils.JwtUtils;
import com.example.ebookserver.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public LoginData adminLogin(Admin admin) {
        Map<String, Object> claims = new HashMap<>();
        LoginData loginData = new LoginData();
        String password = admin.getPassword();
        System.out.println(password);
        claims.put("id", admin.getId());
        claims.put("name", admin.getName());
        loginData.setId(admin.getId());
        String jwt = JwtUtils.generateJwt(claims);
        loginData.setToken(jwt);
        String encodePassword = MD5Util.encode(admin.getPassword());
        LoginData result = adminMapper.getByIdAndPassword(admin.getId(), encodePassword);
        if (result == null) {
            loginData.setCode(1);
            return loginData;
        }
        loginData.setName(result.getName());
        loginData.setCode(3);
        return loginData;
    }

    @Override
    public Admin createAdmin(Admin admin) {
        short role = 2;
        admin.setRole(role);
        //TODO:自定义密码
        String password = this.generatePassword();
        admin.setPassword(password);
        adminMapper.insertAdmin(admin);
        return admin;
    }

    private String generatePassword() {
        Random random = new Random();
        int randomNumber = random.nextInt(90000000) + 10000000;  // 生成 10000000 到 99999999 的随机数
        return Integer.toString(randomNumber);
    }
}

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
        loginData.setRole(result.getRole());
        if (result.getRole() == 100) {
            // 如果角色为100，则设置code为100
            loginData.setCode(100);
        } else {
            loginData.setCode(3);
        }
        return loginData;
    }

    @Override
    public Admin createAdmin(Admin admin) {
        short role = 2;
        admin.setRole(role);
        //TODO:自定义密码
        String password = this.generatePassword();
        admin.setPassword(MD5Util.encode(password));
        admin.setName(admin.getName());
        adminMapper.insertAdmin(admin);
        System.out.println("admin" + admin);
        admin.setPassword(password);
        return admin;
    }
    @Override
    public boolean updateRole(int adminId, int role) {
        try {
            // 编写 SQL 语句
            String sql = "UPDATE admin SET role=? WHERE id=?";

            // 创建 PreparedStatement 对象，并设置参数
            adminMapper.updateRole(role, adminId);

            // 执行更新操作
            int rows = adminMapper.updateRole(role, adminId);

            // 根据返回结果判断是否更新成功
            if (rows > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            // 异常处理
            e.printStackTrace();
            return false;
        }
    }

    private String generatePassword() {
        Random random = new Random();
        int randomNumber = random.nextInt(90000000) + 10000000;  // 生成 10000000 到 99999999 的随机数
        return Integer.toString(randomNumber);
    }
}

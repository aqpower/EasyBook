package com.book.ebookserver.service.manage;

import com.book.ebookserver.mapper.AdminMapper;
import com.book.ebookserver.pojo.Admin;
import com.book.ebookserver.service.AdminService;
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
        admin.setPassword(this.generatePassword());
        adminMapper.insert(admin);
        return admin;
    }

    private String generatePassword() {
        Random random = new Random();
        int randomNumber = random.nextInt(90000000) + 10000000;  // 生成 10000000 到 99999999 的随机数
        return Integer.toString(randomNumber);
    }
}

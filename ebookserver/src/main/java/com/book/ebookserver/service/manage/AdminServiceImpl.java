package com.book.ebookserver.service.manage;

import com.book.ebookserver.mapper.AdminMapper;
import com.book.ebookserver.pojo.Admin;
import com.book.ebookserver.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        } else if (admin.getAdminrname() != null) {
            String username = admin.getAdminrname();
            return adminMapper.getByUsername(username, password);
        } else {
            return null;
        }
    }
    @Override
    public void createAdmin(Admin admin) {
        // 在这里实现 createAdmin() 方法
    }
}

package com.example.ebookserver.service;

import com.example.ebookserver.pojo.Admin;
import com.example.ebookserver.pojo.LoginData;
import com.example.ebookserver.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
/**
 * @author wzb
 */
@Component
public interface AdminService  {
    //Admin adminLogin(Admin admin);
    LoginData adminLogin(Admin admin);
    Admin createAdmin(Admin admin,Integer adminId);
    boolean updateRole(int adminId, int role);
}

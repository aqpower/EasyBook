package com.example.ebookserver.service;

import com.example.ebookserver.pojo.Admin;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
/**
 * @author wzb
 */
@Component
public interface AdminService  {
    Admin adminLogin(Admin admin);

    Admin createAdmin(Admin admin);
}

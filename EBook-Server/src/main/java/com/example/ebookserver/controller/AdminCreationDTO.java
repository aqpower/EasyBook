package com.example.ebookserver.controller;

import com.example.ebookserver.pojo.Admin;

/**
 * @author wzb
 */
public class AdminCreationDTO {
    private Admin admin;
    private Integer adminId;

    // Getters and setters
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}

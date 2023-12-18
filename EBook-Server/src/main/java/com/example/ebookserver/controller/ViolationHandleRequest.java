package com.example.ebookserver.controller;


public class ViolationHandleRequest {
    private Integer type; // 举报处理类型
    private Integer violationId; // 违规举报id
    private Integer adminId; // 管理员id

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getViolationId() {
        return violationId;
    }

    public void setViolationId(Integer violationId) {
        this.violationId = violationId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}

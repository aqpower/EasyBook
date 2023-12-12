package com.example.ebookserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginData {
    int code; //登录的状态码  1：账号不存在  2：密码错误 3.登录成功
    String token;
    Integer id;
    String name;
    Short role; // 用户角色

    public Short getRole() {
        return role;
    }

    public void setRole(Short role) {
        this.role = role;
    }
}
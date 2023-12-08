package com.example.ebookserver.service;


import com.example.ebookserver.pojo.User;

public interface UserService {
    User userLogin(User user);

    int register(User user);
}

package com.example.ebookserver.service;


import com.example.ebookserver.pojo.BlackList;
import com.example.ebookserver.pojo.LoginData;
import com.example.ebookserver.pojo.User;

public interface UserService {
    LoginData userLogin(User user);

    int register(User user);

    boolean verifyEmail(String email);

    boolean delete(Integer id, String password);

    int toBlackList(BlackList blackList);
}

package com.example.ebookserver.service.manage;

import com.example.ebookserver.mapper.UserMapper;
import com.example.ebookserver.pojo.User;
import com.example.ebookserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIml implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User userLogin(User user) {
        return userMapper.userLogin(user);

    }
}

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
        String password = user.getPassword();
        if(user.getId() != null){
            Integer id = user.getId();
            return userMapper.getById(id,password);
        } else if (user.getEmail() != null) {
            String email = user.getEmail();
            return userMapper.getByEmail(email,password);
        }else return null;


    }

    @Override
    public int register(User user) {
        String email = user.getEmail();
        if (userMapper.selectByEmail(email) != null){
            return 0;
        }else {
            userMapper.registerNewUser(user);
            return 1;
        }
    }
}

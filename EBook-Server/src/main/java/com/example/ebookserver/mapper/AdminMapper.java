package com.example.ebookserver.mapper;

import com.example.ebookserver.pojo.Admin;
import com.example.ebookserver.pojo.LoginData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {
    LoginData getByIdAndPassword(@Param("id") Integer id, @Param("password") String password);
    LoginData getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    void insertAdmin(Admin admin);
}

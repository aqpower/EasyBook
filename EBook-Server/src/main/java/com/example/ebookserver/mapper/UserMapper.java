package com.example.ebookserver.mapper;

import com.example.ebookserver.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Select("select * from user where id = #{id} and password = #{password}")
    User getById(Integer id, String password);

    @Select("select * from user where email = #{email} and password = #{password}")
    User getByEmail(String email, String password);
}

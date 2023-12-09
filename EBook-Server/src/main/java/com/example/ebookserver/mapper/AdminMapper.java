package com.example.ebookserver.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ebookserver.pojo.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("select * from admin where id = #{id} and password = #{password}")
    Admin getById(Integer id, String password);

    @Select("select * from admin where username = #{username} and password = #{password}")
    Admin getByUsername(String username, String password);

    @Insert("insert into admin (name, password, role) VALUES (#{name}, #{password}, #{role})")
    void insertAdmin(String name, String password, short role);

}

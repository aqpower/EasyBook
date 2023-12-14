package com.example.ebookserver.mapper;

import com.example.ebookserver.pojo.Admin;
import com.example.ebookserver.pojo.LoginData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    LoginData getByIdAndPassword(@Param("id") Integer id, @Param("password") String password);

    void insertAdmin(Admin admin);
    int updateRole(int role, int adminId);

    @Select("select role from admin where id = #{id}")
    short selectRole(Integer id);
}

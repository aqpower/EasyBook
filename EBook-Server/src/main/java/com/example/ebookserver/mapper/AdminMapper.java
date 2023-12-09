package com.book.ebookserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.book.ebookserver.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("select * from admin where id = #{id} and password = #{password}")
    Admin getById(Integer id, String password);

    @Select("select * from admin where username = #{username} and password = #{password}")
    Admin getByUsername(String username, String password);
}

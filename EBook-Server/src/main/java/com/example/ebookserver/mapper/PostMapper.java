package com.example.ebookserver.mapper;

import com.example.ebookserver.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {
    /*
    * 查询用户的权限
    * */
    @Select("select role from user where id = #{userId}")
    int selectRole(Integer userId);

    /*
    * 用户发布帖子
    * */
    int post(Post post);

    List<Post> postById(List<Integer> users);
}

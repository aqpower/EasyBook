package com.example.ebookserver.mapper;

import com.example.ebookserver.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    /*
    * id登录
    * */
    @Select("select name from user where id = #{id}")
    String  getById(Integer id);
    @Select("select name from user where id = #{id} and password = #{password}")
    String getByIdAndPassword(Integer id,String password);
    /*
     * 邮箱登录
     * */
    @Select("select name from user where email = #{email}")
    String  getByEmail(String email);
    @Select("select name from user where email = #{email} and password = #{password}")
    String  getByEmailAndPassword(String email,String password);
    /*
     * 邮箱查重
     * */
    @Select("select id from user where email= #{email}")
    String selectByEmail(String email);

    /*
     * 注册，邮箱，名字，密码，头像
     * */
    @Insert("insert into user (email, name, password, avatar) values (#{email},#{name},#{password},#{avatar})")
    int registerNewUser(User user);

    /*
    * 注销用户：通过id删除
    * */
    @Delete("delete from user where id = #{id}")
    void deleteById(Integer id);

    /*
    * 查找用户的拉黑列表
    * */
    @Select("select black_user_id from blacklist where user_id = #{userId}")
    List<Integer> selectBlackList(Integer userId);

    /*
    * 查询拉黑了用户的列表
    * */
    @Select("select user_id from blacklist where black_user_id = #{blackUserId}")
    List<Integer> selectBlackedList(Integer blackUserId);
    /*
    * 拉黑用户：插入数据
    * */
    @Insert("insert into blacklist(user_id, black_user_id) values (#{userId},#{blackUserid})")
    int insertToBlackList(Integer userId, Integer blackUserid);

    @Update("update user set name = #{name},avatar = #{avatar} where id = #{id}")
    int updateById(User user);
}

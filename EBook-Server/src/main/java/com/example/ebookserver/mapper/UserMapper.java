package com.example.ebookserver.mapper;

import com.example.ebookserver.pojo.Care;
import com.example.ebookserver.pojo.LoginData;
import com.example.ebookserver.pojo.Notify;
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
    @Select("select id,name,avatar from user where email= #{email}")
    LoginData selectByEmail(String email);

    /*
     * 注册，邮箱，名字，密码，头像
     * */
    @Insert("insert into user (email, name, password, avatar) values (#{email},#{name},#{password},#{avatar})")
    int registerNewUser(User user);

    /*
     * 注销用户：通过id删除
     * */
    @Update("update user set role = 100 where id = #{id}")
    int deleteById(Integer id);

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


    int updateById(User user);

    @Select("select role from user where id = #{id}")
    short getRoleById(Integer id);
    @Select("select role from user where email = #{email}")
    short getRoleByEmail(String email);

    @Select("select avatar from user where user.id = #{id}")
    short selectAvatarById(Integer id);

    @Select("select avatar from user where user.email = #{email}")
    short selectAvatarByEmail(String email);

    @Select("select id,name,avatar from user where id = #{id}")
    User selectUserDetailsByid(Integer id);


    List<Notify> selectNotifies(List<Integer> ids, Integer start, Integer pageSize);


    Long countNotify(List<Integer> ids);

    @Select("select id,name,avatar from user where id = #{id}")
    LoginData selectById(Integer id);

    @Insert("insert into care (care_user_id, cared_user_id) values (#{careUserId},#{caredUserId})")
    void AddCare(Care care);

    @Select("select cared_user_id from care where care_user_id = #{careUserId}")
    List<Integer> selectCaresId(Integer careUserId);

    @Select("select user.id,name,avatar from user,care where cared_user_id = user.id and care_user_id = #{id}")
    List<User> selectCares(Integer id);

    @Delete("delete from care where care_user_id = #{careUserId} and cared_user_id = #{caredUserId}")
    void UnCare(Care care);
}

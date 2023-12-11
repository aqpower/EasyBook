package com.example.ebookserver.service.manage;

import com.example.ebookserver.mapper.UserMapper;
import com.example.ebookserver.pojo.BlackList;
import com.example.ebookserver.pojo.LoginData;
import com.example.ebookserver.pojo.User;
import com.example.ebookserver.service.UserService;
import com.example.ebookserver.utils.JwtUtils;
import com.example.ebookserver.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public LoginData userLogin(User user) {
        /*
        * 查询账号是否存在
        * 查询账号对应密码是否正确
        * 生成返回对象
        * */
        LoginData loginData = new LoginData();

        Map<String, Object> claims = new HashMap<>();
        if(user.getId() != null){  //id登录
            String name = userMapper.getById(user.getId());
            if (name !=null){  //账号存在
                short role = userMapper.getRoleById(user.getId());
                if (role <= 4){
                    if(userMapper.getByIdAndPassword(user.getId(), MD5Util.encode(user.getPassword())) != null){  //登录成功
                        loginData.setId(user.getId());
                        loginData.setName(name);
                        claims.put("id",user.getId());
                        claims.put("name",name);
                        loginData.setCode(3);
                    }else {  //密码错误
                        loginData.setCode(2);
                    }
                }else {
                    loginData.setCode(5);
                }

            }else{   //账号不存在
                loginData.setCode(1);
            }
        } else if (user.getEmail() != null) {   //邮箱登录
            String name = userMapper.getByEmail(user.getEmail());
            if (name != null){  //账号存在
                short role = userMapper.getRoleByEmail(user.getEmail());
                if (role <= 4){
                    if(userMapper.getByEmailAndPassword(user.getEmail(),MD5Util.encode(user.getPassword())) != null){  //登录成功
                        claims.put("name",name);
                        claims.put("email",user.getEmail());
                        loginData.setName(name);
                        loginData.setCode(3);
                    }else {  //密码错误
                        loginData.setCode(2);
                    }
                }else {
                    loginData.setCode(5);
                }
            }else{   //账号不存在
                loginData.setCode(1);
            }
        }
        if(loginData.getCode() == 3){
            String jwt = JwtUtils.generateJwt(claims);
            loginData.setToken(jwt);
        }
        return loginData;
    }

    /*
    * 注册用户
    * */
    @Override
    public int register(User user) {
        String password = user.getPassword();
        user.setPassword(MD5Util.encode(password));  //MD5加密后存储
        return userMapper.registerNewUser(user);
    }

    /*
    * 验证邮箱是否有效
    * */
    @Override
    public boolean verifyEmail(String email) {
        if (userMapper.selectByEmail(email) != null) {
            return false;
        }
        return true;
    }

    /*
    * 注销用户
    * */
    @Override
    public boolean delete(Integer id, String password) {
        if (userMapper.getByIdAndPassword(id,MD5Util.encode(password)) != null){
            userMapper.deleteById(id);
            return true;
        }
        return false;
    }

    /*
    * 拉黑用户
    * */
    @Override
    public int toBlackList(BlackList blackList) {
        int userId = blackList.getUserId();
        int blackUserId = blackList.getBlackUserId();
        List<Integer> integers = selectBlackUsers(userId); //查找用户的拉黑列表
        for (Integer integer : integers) {
            /*
            * 如果已经拉黑该用户则无法再次拉黑
            * */
            if (integer == blackUserId)
                return 0;
        }
        String user_name = userMapper.getById(userId);
        String black_user_name = userMapper.getById(blackUserId);
        if ((userId != blackUserId) && user_name != null && black_user_name != null) {
            /*
            * 确保拉黑用户与被拉黑用户都存在且不为同一人
            * */
            return userMapper.insertToBlackList(userId, blackUserId);
        }
        return 0;
    }

    @Override
    public int update(User user) {
        int result = userMapper.updateById(user);
        return result;
    }

    /*
    * 查找用户的拉黑列表
    * */
    @Override
    public List<Integer> selectBlackUsers(Integer id) {
        return userMapper.selectBlackList(id);
    }

    /*
     * 查找拉黑了用户的列表
     * */
    @Override
    public List<Integer> selectBlackedUsers(Integer id) {
        return userMapper.selectBlackedList(id);
    }
}

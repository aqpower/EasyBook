package com.example.ebookserver.service.manage;

import com.example.ebookserver.mapper.PostMapper;
import com.example.ebookserver.mapper.UserMapper;
import com.example.ebookserver.pojo.*;
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
    @Autowired
    private PostMapper postMapper;
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
                        loginData = userMapper.selectById(user.getId());
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
                        loginData = userMapper.selectByEmail(user.getEmail());
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
            claims.put("id",user.getId());
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
        if (userMapper.selectByEmail(email) != null)  return false;
        return true;
    }

    /*
     * 注销用户
     * */
    @Override
    public boolean delete(Integer id, String password) {
        if (userMapper.getByIdAndPassword(id,MD5Util.encode(password)) != null){
            if (userMapper.deleteById(id) == 1)
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
        return userMapper.updateById(user);
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

    /*
    * userId:被查看主页的id
    * id:查看他人主页的id
    * */
    @Override
    public Home selectUserDetails(Integer userId, Integer id) {
        Home home = new Home();
        //基本信息
        home = userMapper.selectUserDetailsByid(userId);
        //查看关注的数量
        List<Integer> caresId = userMapper.selectCaresId(userId);
        //查看粉丝的数量
        List<Integer> fansId = userMapper.selectFansId(userId);
        //查看拉黑列表
        List<Integer> blacksId = userMapper.selectBlackList(id);

        home.setFansNum((short) fansId.size());
        home.setFollowNum((short) caresId.size());

        if (fansId.contains(id)){
            home.setFollowed(true);
        }else {
            home.setFollowed(false);
        }
        if (blacksId.contains(userId)){
            home.setBlacked(true);
        }else {
            home.setBlacked(false);
        }
        return home;
    }

    @Override
    public NotifyPageBean selectNotifies(Integer id, Integer page, Integer pageSize) {

        //查询该用户所有帖子的id
        List<Integer> ids = postMapper.selectIdByUserId(id);
        if (ids.size() != 0){
            //与该用户关联的所有通知
            Long count = userMapper.countNotify(ids);
            Integer start = (page-1) * pageSize;
            //分页查询的结果
            List<Notify> notifyList = userMapper.selectNotifies(ids, start, pageSize);
            NotifyPageBean notifyPageBean = new NotifyPageBean((count+pageSize-1)/pageSize,notifyList);
            return notifyPageBean;
        }
        return null;
    }
    @Override
    public int getUserCount(){
        return userMapper.getUserCount();
    }


    @Override
    public void toCare(Care care) {
        userMapper.AddCare(care);
    }

    @Override
    public List<Integer> selectCareListId(Integer careUserId) {
        return userMapper.selectCaresId(careUserId);
    }

    @Override
    public List<User> selectCareList(Integer id) {
        return userMapper.selectCares(id);
    }

    @Override
    public void toUnCare(Care care) {
        userMapper.UnCare(care);
    }

    @Override
    public List<User> selectFansList(Integer id) {
        return userMapper.selectFans(id);
    }

    @Override
    public void updateUseRole(Integer id, short role) {
        userMapper.updateUserRoleById(id, role);
    }

    @Override
    public int DisBlackList(BlackList blackList) {
        List<Integer> blackId = userMapper.selectBlackList(blackList.getUserId());
        //确保拉黑过
        if (blackId.contains(blackList.getBlackUserId())){
            return userMapper.disBlack(blackList);
        }
        return 3;
    }

    @Override
    public List<MostPopularAvatar> getMostPopularAvatar() {
        return userMapper.selectMostPopularAvatar();
    }


    @Override
    public CommentBean selectComments(Integer id, Integer page, Integer pageSize) {
        Integer start = (page-1) * pageSize;
        Long count = userMapper.countComment(id);
        List<Comment> commentList = userMapper.pageComments(id,start,pageSize);
        return new CommentBean((count+pageSize-1)/pageSize,commentList);
    }
}

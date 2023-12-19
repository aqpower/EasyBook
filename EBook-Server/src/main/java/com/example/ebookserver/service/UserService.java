package com.example.ebookserver.service;


import com.example.ebookserver.pojo.*;

import java.util.List;

public interface UserService {
    LoginData userLogin(User user);

    int register(User user);

    boolean verifyEmail(String email);

    boolean delete(Integer id, String password);

    int toBlackList(BlackList blackList);

    int update(User user);
    List<Integer> selectBlackUsers(Integer id);
    List<Integer> selectBlackedUsers(Integer id);

    Home selectUserDetails(Integer id, Integer integer);

    NotifyPageBean selectNotifies(Integer id, Integer page, Integer pageSize);

    void toCare(Care care);

    List<Integer> selectCareListId(Integer careUserId);

    List<User> selectCareList(Integer id);

    void toUnCare(Care care);

    List<User> selectFansList(Integer id);

    CommentBean selectComments(Integer id, Integer page, Integer pageSize);

    void updateUseRole(Integer id, short role);

    int DisBlackList(BlackList blackList);

    int getUserCount();

    List<MostPopularAvatar> getMostPopularAvatar();


    List<MostPopularAvatar> getMostPopularAvatar();

}

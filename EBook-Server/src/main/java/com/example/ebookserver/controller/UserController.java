package com.example.ebookserver.controller;

import com.example.ebookserver.pojo.BlackList;
import com.example.ebookserver.pojo.Result;
import com.example.ebookserver.pojo.User;
import com.example.ebookserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    /*
     * 用户注销功能实现
     * 获取参数：json{password}
     * 返回参数：json{code,msg,data}
     * */
    @DeleteMapping("/api/v1/delete/user")
    public Result user_delete(@RequestBody User user){
        log.info("实现用户注销功能");
        boolean result = userService.delete(user.getId(),user.getPassword());
        if (result){
            return Result.success();
        }
        return Result.error("密码输入错误，注销失败了");
    }
    /*
     * 用户拉黑功能实现
     * 获取参数：json{userId,blackUserId}
     * 返回参数：相应的提示语句
     * */
    @PostMapping("/api/v1/blackList")
    public Result user_blackList(@RequestBody BlackList blackList){
        log.info("实现用户拉黑功能");
        int result = userService.toBlackList(blackList);
        if (result == 1){
            return Result.success("拉黑成功");
        }
        return Result.error("拉黑失败，请确保id有效");
    }
    /*
    * 用户更改个人信息功能实现
    * 获取参数：json{id,name,
    * */
    @PutMapping("/api/v1/user")
    public Result user_update(@RequestBody User user){
        log.info("实现用户修改个人信息功能");
        int result = userService.update(user);
        if (result == 1){
            return Result.success();
        }
        return Result.error("修改失败");
    }
}

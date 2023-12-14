package com.example.ebookserver.controller;

import com.example.ebookserver.pojo.*;
import com.example.ebookserver.service.PostService;
import com.example.ebookserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    /*
     * 用户注销功能实现
     * 获取参数：json{password}
     * 返回参数：json{code,msg,data}
     * */
    @DeleteMapping("/delete/user")
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
    @PostMapping("/blackList")
    public Result user_blackList(@RequestBody BlackList blackList){
        log.info("实现用户拉黑功能");
        int result = userService.toBlackList(blackList);
        if (result == 1){
            return Result.success("拉黑成功");
        }
        return Result.error("拉黑失败，请确保id有效");
    }
    /*
    * 根据用户id查询所有帖子
    * */
    @GetMapping("/posts/users/{userId}")
    public Result getPosts(@PathVariable Integer userId){
        log.info("查询某个用户发的全部帖子");
        List<Posts> posts = postService.getPosts(userId);
        return Result.success(posts);
    }

    /*
    * 用户更改个人信息功能实现
    * 获取参数：json{id,name,
    * */
    @PutMapping("/user")
    public Result user_update(@RequestBody User user){
        log.info("实现用户修改个人信息功能");
        int result = userService.update(user);
        if (result == 1){
            return Result.success();
        }
        return Result.error("修改失败");
    }

    /*
    * 用户主页展示信息
    * */
    @GetMapping("/users/info/{id}")
    public Result userDetail(@PathVariable Integer id){
        log.info("用户主页展示");
        User user = userService.selectUserDetails(id);
        return Result.success(user);
    }

    /*
     * 通知展示接口
     * */
    @GetMapping("/user/notify")
    public Result AllNotifies(@RequestParam Integer id,
                              @RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("用户查看通知");
        NotifyPageBean notifyPageBean = userService.selectNotifies(id, page, pageSize);
        return Result.success(notifyPageBean);
    }
}

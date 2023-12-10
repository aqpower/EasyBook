package com.example.ebookserver.controller;

import com.example.ebookserver.pojo.PageBean;
import com.example.ebookserver.pojo.Post;
import com.example.ebookserver.pojo.Result;
import com.example.ebookserver.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class PostController {
    @Autowired
    private PostService postService;

    /*
    *
    * 用户发布帖子
    * */
    @PostMapping("/post")
    public Result post(@RequestBody Post post){
        log.info("用户尝试发布帖子");
        int result = postService.toPost(post);
        if (result == 1){
            return Result.success();
        } else if (result == 3)  {
            return Result.error("对不起，你没有发布帖子的权限");
        }else
            return Result.error("抱歉，发布失败");
    }

    /*
    * 系统主页展示所有的帖子，除了使用者拉黑的用户，和拉黑了使用者的用户
    * */
    @GetMapping("/page")
    public Result page(@RequestParam Integer id,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页查询");
        PageBean pageBean = postService.allPOst(id,page,pageSize);
        return Result.success(pageBean);
    }
//    @GetMapping("/allPost")
//    public Result allPost(Integer id){
//        log.info("展示主页全部帖子");
//        List<PostShow> posts = postService.allPOst(id, pageSize, page);
//        return Result.success(posts);
//    }
}

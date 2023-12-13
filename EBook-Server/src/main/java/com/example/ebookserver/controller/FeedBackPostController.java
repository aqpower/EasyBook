package com.example.ebookserver.controller;

import com.example.ebookserver.pojo.*;
import com.example.ebookserver.service.FeedBackPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/v1")
public class FeedBackPostController {

    @Autowired
    private FeedBackPostService feedBackPostService;
    /*
    * 点赞帖子
    * */
    @PostMapping("/like")
    public Result like(@RequestBody EasyLike easyLike){
        log.info("用户点赞帖子");
        int result = feedBackPostService.toLike(easyLike);
        if (result == 1){
            return Result.success();
        }else if (result == 3){
            return Result.error("已经点过赞了");
        }
        return Result.error("点赞失败");
    }
    /*
    * 用户收藏帖子
    * */
    @PostMapping("/collection")
    public Result collection(@RequestBody Collection collection){
        log.info("用户收藏帖子");
        int result = feedBackPostService.toCollection(collection);
        if (result == 1){
            return Result.success();
        }else if (result == 3){
            return Result.error("已经收藏过了");
        }
        return Result.error("收藏失败");
    }
    /*
    * 用户评论帖子
    * */
    @PostMapping("/comment")
    public Result comment(@RequestBody Comment comment){
        log.info("用户发表评论");
        int result = feedBackPostService.toComment(comment);
        if(result == 1){
            return Result.success();
        }
        return Result.error("评论失败");
    }
    /*
    * 用户举报帖子
    * */
    @PostMapping("/violation")
    public Result violation(@RequestBody Violation violation){
        log.info("用户举报帖子");
        return Result.error("举报失败");
    }
}

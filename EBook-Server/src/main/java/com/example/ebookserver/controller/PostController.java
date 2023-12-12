package com.example.ebookserver.controller;

import com.example.ebookserver.pojo.PageBean;
import com.example.ebookserver.pojo.Post;
import com.example.ebookserver.pojo.Result;
import com.example.ebookserver.service.PostService;
import com.example.ebookserver.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private AliOSSUtils aliOSSUtils;

    private List<String> urls = new ArrayList<>();

    /*
    * 用户上传图片
    * */
    @PostMapping("/upload")
    public Result upload(MultipartFile[] image) throws IOException {
        log.info("文件上传");
        for (MultipartFile multipartFile : image) {
            String url = aliOSSUtils.upload(multipartFile);
            urls.add(url);
        }
        return Result.success(urls);
    }

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
    @GetMapping("/posts")
    public Result page(@RequestParam Integer id,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页查询");
        PageBean pageBean = postService.allPOst(id,page,pageSize);
        return Result.success(pageBean);
    }


   // List<MultipartFile> multipartFiles;
//    @GetMapping("/allPost")
//    public Result allPost(Integer id){
//        log.info("展示主页全部帖子");
//        List<PostShow> posts = postService.allPOst(id, pageSize, page);
//        return Result.success(posts);
//    }

}

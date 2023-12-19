package com.example.ebookserver.controller;

import com.example.ebookserver.pojo.PageBean;
import com.example.ebookserver.pojo.Post;
import com.example.ebookserver.pojo.PostDetails;
import com.example.ebookserver.pojo.Result;
import com.example.ebookserver.service.PostService;
import com.example.ebookserver.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        urls.clear();
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

//    /*
//    * 系统主页展示所有的帖子，除了使用者拉黑的用户，和拉黑了使用者的用户
//    * */
//    @GetMapping("/posts")
//    public Result page(@RequestParam Integer id,
//                       @RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize){
//        log.info("分页查询");
//        PageBean pageBean = postService.allPOst(id,page,pageSize);
//        return Result.success(pageBean);
//    }

    @GetMapping("/posts/{postId}")
    public Result postDetails(@PathVariable Integer postId){
        log.info("查看某个帖子的详细信息");
        PostDetails postDetails = postService.selectDetails(postId);
        if (postDetails != null){
            return Result.success(postDetails);
        }
        return Result.error("该帖子不存在！");
    }

    /*
     * 用户删除帖子
     * */
    @DeleteMapping("/posts/{id}")
    public Result deletePost(@PathVariable Integer id){
        log.info("删除帖子");
        postService.deletePost(id);
        return Result.success();
    }

    /*
     * 用户根据关键字搜索帖子
     * */
    @GetMapping("/posts")
    public Result search( Short color,
                          String text,
                         @RequestParam Integer id,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("关键字分类分页查询");
        PageBean pageBean = postService.search(color,text,id,page,pageSize);
        return Result.success(pageBean);
    }
    /*
    *管理员获取帖子数量
    * */
    @GetMapping("/posts/today/count")
    public Map<String,Integer> getTodayPostCount(){
        int count = postService.getTodayPostCount();

        Map<String,Integer> response = new HashMap<>();
        response.put("count",count);

        return response;
    }

    @GetMapping("/visit")
    public ResponseEntity<?> getTotalViewCount() {
        int count = postService.getTotalViewCount();
        return ResponseEntity.ok().body(new CountResponse(count));
    }

    private static class CountResponse {
        private final int count;

        public CountResponse(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }
    }


    /*
    *系统所有的帖子
    * */

    @GetMapping("/posts/all")
    public Result AllPosts(){
        log.info("查询系统所有帖子的总数");
        int count = postService.countAllPosts();
        return Result.success(count);
    }
}

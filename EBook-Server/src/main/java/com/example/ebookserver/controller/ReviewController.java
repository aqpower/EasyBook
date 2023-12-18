package com.example.ebookserver.controller;

import com.example.ebookserver.pojo.Posts;
import com.example.ebookserver.pojo.Result_Admin;
import com.example.ebookserver.pojo.Review;
import com.example.ebookserver.pojo.Violation;
import com.example.ebookserver.service.ReviewService;
import com.example.ebookserver.service.UserService;
import com.example.ebookserver.service.ViolationService;
import com.example.ebookserver.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/admin/operations")
public class ReviewController {
    @Autowired
    private  ReviewService reviewService;
    @Autowired
    private  ViolationService violationService;
    @Autowired
    private  PostService postService;
    @Autowired
    private UserService userService;

    public ReviewController(ReviewService reviewService, ViolationService violationService, PostService postService) {
        this.reviewService = reviewService;
        this.violationService = violationService;
        this.postService = postService;
    }

    /**
     * 创建一条举报处理结果记录
     *
     * @param request 包含type、violationId和adminId的请求对象
     * @return String 创建结果信息
     */
    @PostMapping
    public Result_Admin createReview(@RequestBody ViolationHandleRequest request) {
        Integer type = request.getType();
        Integer violationId = request.getViolationId();
        Integer adminId = request.getAdminId();

        // 创建Review对象并设置相关属性
        Review review = new Review();
        review.setAdminId(adminId);
        review.setViolationId(violationId);
        review.setReviewTime(new Date());
        review.setReviewResult(type.toString());

        // 调用reviewService来创建Review记录
        boolean success = reviewService.createReview(review);

        // 通过violationId从数据库中获取postId
        Violation violation = violationService.getViolationById(violationId);
        Integer postId = violation.getPostId();
        Integer userId = postService.getUserIdById(postId);

        if (type == 1) {
            // 如果type为1，则执行删除帖子的操作
            userService.updateUseRole(userId, Short.parseShort(String.valueOf(5)));
        } else if (type == 2) {
            userService.updateUseRole(userId, Short.parseShort(String.valueOf(4)));
        }
        postService.deletePost(postId);


        // 根据创建结果返回相应信息
        if (success) {
            return Result_Admin.success("创建举报处理结果成功");
        } else {
            return Result_Admin.success("创建举报处理结果失败");
        }
    }

}

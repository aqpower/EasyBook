package com.example.ebookserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/*
*
* 帖子详情页面展示需要的评论相关内容
* 评论者id，名字，头像，评论内容，时间
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comments {
    private Integer id;
    private Integer userId;
    private String name;
    private short avatar;
    private String content;
    private LocalDateTime commentTime;

}

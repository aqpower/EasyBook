package com.example.ebookserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/*
* 展示帖子的详细信息，返回给前端
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posts {
    private Integer id; //帖子id  post
    private short avatar;  //发帖人头像  user
    private String name;  //发帖人昵称   user
    private String title; //标题   post
    private String contentText; //文字内容 post
    private short color;  //帖子颜色  post
    private String lyrics;  //歌词  post
    private List<String> url;//图片链接集合 image
    private Integer likeNum;  //喜欢数量  post
    private Integer collectionNum;  //收藏数量 post
}

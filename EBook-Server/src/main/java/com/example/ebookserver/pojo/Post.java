package com.example.ebookserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Integer id;
    private short type;
    private String title;
    private String contentText;
    private short color;
    private String lyrics;
    private Integer likeNum;
    private Integer collectionNum;
    private String ip;
    private Integer violationNum;
    private Integer userId;
}

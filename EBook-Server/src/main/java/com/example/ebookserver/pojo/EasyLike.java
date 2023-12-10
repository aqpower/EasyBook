package com.example.ebookserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EasyLike {
    private Integer id;
    private Integer userId;
    private Integer postId;
    private LocalDateTime likeTime;
}

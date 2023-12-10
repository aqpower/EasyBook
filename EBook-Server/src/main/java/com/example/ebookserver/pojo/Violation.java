package com.example.ebookserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Violation {
    private Integer id;
    private Integer userId;
    private Integer postId;
    private LocalDateTime violationTime;
    private String  violationReason;
}

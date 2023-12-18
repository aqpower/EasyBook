package com.example.ebookserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Integer id; // 自增id
    private Integer adminId; // 管理员id
    private Integer violationId; // 违规举报id
    private Date reviewTime; // 处理时间
    private String reviewResult; // 处理结果

    // getter 和 setter
}

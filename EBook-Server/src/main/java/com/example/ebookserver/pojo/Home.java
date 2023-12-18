package com.example.ebookserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Home {
    Integer id;
    String name;
    short avatar;
    short followNum;
    short fansNum;
    boolean isFollowed;
    boolean isBlacked;
}

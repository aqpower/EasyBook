package com.example.ebookserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wzb
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    Integer id;
    String name;
    String password;
    short role;
}

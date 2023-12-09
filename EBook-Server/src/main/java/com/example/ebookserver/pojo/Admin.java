package com.book.ebookserver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("admin")
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
    String password;
    short role;

    public String getName() {
        return name;
    }
}

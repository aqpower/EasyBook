package com.example.ebookserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    public Integer id;
    public String name;
    public String password;
    public short role;

    public short getRole() {
        return this.role;
    }
}

package com.book.ebookserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result_Admin {
    private Integer code;  // 响应码，200代表成功; 401代表失败
    private String msg;    // 响应信息描述字符串
    private Object data;   // 返回的数据

    // 成功响应
    public static Result_Admin success() {
        return new Result_Admin(200, "success", null);
    }

    // 成功响应
    public static Result_Admin success(Object data) {
        return new Result_Admin(200, "success", data);
    }

    // 失败响应
    public static Result_Admin error(String msg) {
        return new Result_Admin(401, msg, null);
    }
}

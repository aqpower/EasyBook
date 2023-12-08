package com.example.ebookserver.controller;

import com.example.ebookserver.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
public class UserController {
    @GetMapping("/api/v1/hello")
    public Result hello(){
        return Result.success("hello~");
    }

}

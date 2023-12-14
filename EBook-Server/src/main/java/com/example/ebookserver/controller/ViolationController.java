package com.example.ebookserver.controller;


import com.example.ebookserver.pojo.PgBean;
import com.example.ebookserver.pojo.Result;
import com.example.ebookserver.service.ViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin
public class ViolationController {
    @Autowired
    private ViolationService violationService;

    @GetMapping("/login/reports")
    public Result page(@RequestParam(defaultValue="1")Integer page,
                       @RequestParam(defaultValue="1")Integer pageSize){
        log.info("分页查询，参数：{}，{}",page,pageSize);
        //调用service分页查询
        PgBean pgBean=violationService.page(page,pageSize);
        return Result.success(pgBean);
    }
}

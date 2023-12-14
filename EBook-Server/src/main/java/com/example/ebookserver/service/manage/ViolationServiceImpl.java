package com.example.ebookserver.service.manage;

import com.example.ebookserver.mapper.ViolationMapper;

import com.example.ebookserver.pojo.PgBean;
import com.example.ebookserver.pojo.Violation;
import com.example.ebookserver.service.ViolationService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // 注意这一行

import java.util.List;
@Service
public class ViolationServiceImpl implements ViolationService {
    @Autowired
    private ViolationMapper violationMapper;


    public PgBean page(Integer page, Integer pageSize){
        //获取总记录数
        Long count = violationMapper.count();
        //获取分页查询列表
        Integer start=(page-1)*pageSize;
        List<Violation> violationList = violationMapper.page(start, pageSize);
        //封装PageBean对象
        PgBean pgBean = new PgBean(count,violationList);
        return pgBean;
    }
}

package com.example.ebookserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.example.ebookserver.pojo.PgBean;

public interface ViolationService {
    /**
     * 分页查询
     */
    PgBean page(Integer page, Integer pageSize);
}

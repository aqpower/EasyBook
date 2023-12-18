package com.example.ebookserver.service;

import com.example.ebookserver.pojo.Violation;
import com.example.ebookserver.pojo.PgBean;

public interface ViolationService {
    /**
     * 分页查询
     */
    PgBean page(Integer page, Integer pageSize);

    Violation getViolationById(Integer violationId);

    void deleteViolation(Integer id);

    Violation selectById(Integer violationId);
}

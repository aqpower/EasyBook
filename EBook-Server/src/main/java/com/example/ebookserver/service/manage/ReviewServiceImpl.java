package com.example.ebookserver.service.impl;

import com.example.ebookserver.mapper.ReviewMapper;
import com.example.ebookserver.pojo.Review;
import com.example.ebookserver.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public boolean createReview(Review review) {
        int result = reviewMapper.insert(review);
        return result == 1;
    }
}
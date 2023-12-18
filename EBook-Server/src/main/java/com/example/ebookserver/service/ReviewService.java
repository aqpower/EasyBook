package com.example.ebookserver.service;

import com.example.ebookserver.pojo.Review;

public interface ReviewService {
    /**
     * 创建一条举报处理结果记录
     * @param review 举报处理结果对象
     * @return boolean 创建结果，true表示创建成功，false表示创建失败
     */
    boolean createReview(Review review);
}

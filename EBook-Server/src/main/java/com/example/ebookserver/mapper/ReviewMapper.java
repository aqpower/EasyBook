package com.example.ebookserver.mapper;

import com.example.ebookserver.pojo.Review;
import com.example.ebookserver.pojo.Violation;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ReviewMapper {
    /**
     * 插入一条新的举报处理结果记录
     * @param review 举报处理结果对象
     * @return 插入记录的id
     */
    @Insert("INSERT INTO review(admin_id, violation_id, review_time, review_result) " +
            "VALUES(#{adminId}, #{violationId}, #{reviewTime}, #{reviewResult})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Review review);
}

package com.example.ebookserver.mapper;

import com.example.ebookserver.pojo.Violation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface ViolationMapper {
    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from violation")
    public Long count();

    @Select("select * from violation limit #{start}, #{pageSize}")
    public List<Violation> page(Integer start, Integer pageSize);

    @Select("select * from violation where id = #{violationId}")
    Violation getViolationById(Integer violationId);

    @Delete("delete from violation where id = #{id}")
    void deleteById(Integer id);


}

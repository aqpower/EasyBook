package com.example.ebookserver.mapper;

import com.example.ebookserver.pojo.Collection;
import com.example.ebookserver.pojo.EasyLike;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FeedBackPostMapper {
    /*
    * 查询有没有给帖子点赞过
    * */
    @Select("select * from easylike where user_id = #{userId} and post_id = #{postId}")
    EasyLike findLikeById(Integer userId, Integer postId);

    /*
    * 给帖子点赞
    * */
    @Insert("insert into easylike (user_id, post_id, like_time) values (#{userId},#{postId},#{likeTime})")
    int toLike(EasyLike easyLike);

    /*
     * 查询有没有给帖子收藏过
     * */
    @Select("select * from collection where user_id = #{userId} and post_id = #{postId}")
    Collection findCollectionById(Integer userId, Integer postId);

    /*
     * 收藏
     * */
    @Insert("insert into collection (user_id, post_id, collection_time) values (#{userId},#{postId},#{collectionTime})")
    int toCollection(Collection collection);
}

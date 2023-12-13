package com.example.ebookserver.mapper;

import com.example.ebookserver.pojo.Comments;
import com.example.ebookserver.pojo.Post;
import com.example.ebookserver.pojo.Posts;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {
    /*
     * 查询用户的权限
     * */
    @Select("select role from user where id = #{userId}")
    int selectRole(Integer userId);

    /*
     * 用户发布帖子
     * */
    int post(Post post);

    /*
     * 通过帖子id查对应的imageUrl
     * */
    @Select("select url from image where post_id = #{postId}")
    List<String> getUrl(Integer postId);
    /*
     * 分页查询帖子
     * */
    List<Posts> page(List<Integer> users, Integer start, Integer pageSize);

    /*
     * 查询帖子总数
     * */
    long count(List<Integer> users);

    @Select("select count(*) from image where post_id = 1")
    long count1();

    int toImages(int postId, List<String> urls);

    Posts selectPost(Integer postId);

    List<Comments> selectComments(Integer postId);

    List<Posts> getPostsByUserId(Integer id);

    @Select("select count(*) from comment where post_id = #{postId}")
    Integer countComments(Integer postId);

    @Select("select id from post where user_id = #{id}")
    List<Integer> selectIdByUserId(Integer id);

    @Delete("delete from post where id = #{id}")
    void deleteById(Integer id);
}

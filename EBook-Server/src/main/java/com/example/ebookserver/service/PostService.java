package com.example.ebookserver.service;

import com.example.ebookserver.pojo.PageBean;
import com.example.ebookserver.pojo.Post;
import com.example.ebookserver.pojo.PostDetails;

import java.util.List;

public interface PostService {
    int toPost(Post post);

    //PageBean allPOst(Integer id, Integer pageSize, Integer page);

    PostDetails selectDetails(Integer postId);

    PageBean getPosts(Integer id, Integer page, Integer pageSize);

    void deletePost(Integer id);

    PageBean search(Short color, String text, Integer id, Integer page, Integer pageSize);

    PageBean showCarePosts(Integer id, Integer page, Integer pageSize, List<Integer> careList);

    PageBean getLikePosts(Integer id, Integer page, Integer pageSize);

    PageBean getCollectionPosts(Integer id, Integer page, Integer pageSize);

    void deletePostByViolation(Integer violationId);

    Integer getUserIdById(Integer postId);

    int countAllPosts();
}

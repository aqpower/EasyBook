package com.example.ebookserver.service;

import com.example.ebookserver.pojo.PageBean;
import com.example.ebookserver.pojo.Post;
import com.example.ebookserver.pojo.PostDetails;
import com.example.ebookserver.pojo.Posts;

import java.util.List;

public interface PostService {
    int toPost(Post post);

    PageBean allPOst(Integer id, Integer pageSize, Integer page);

    PostDetails selectDetails(Integer postId);

    List<Posts> getPosts(Integer id);
}

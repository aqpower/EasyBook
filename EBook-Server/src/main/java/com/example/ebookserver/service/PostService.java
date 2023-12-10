package com.example.ebookserver.service;

import com.example.ebookserver.pojo.Post;

import java.util.List;

public interface PostService {
    int toPost(Post post);

    List<Post> allPOst(Integer id);
}

package com.example.ebookserver.service;

import com.example.ebookserver.pojo.PageBean;
import com.example.ebookserver.pojo.Post;

public interface PostService {
    int toPost(Post post);

    PageBean allPOst(Integer id, Integer pageSize, Integer page);
}

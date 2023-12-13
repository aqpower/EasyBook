package com.example.ebookserver.service.manage;

import com.example.ebookserver.mapper.FeedBackPostMapper;
import com.example.ebookserver.pojo.Collection;
import com.example.ebookserver.pojo.Comment;
import com.example.ebookserver.pojo.EasyLike;
import com.example.ebookserver.service.FeedBackPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FeedBackPostServiceImpl implements FeedBackPostService {
    @Autowired
    private FeedBackPostMapper feedBackPostMapper;



    /*
    * 用户点赞
    * */
    @Override
    public int toLike(EasyLike easyLike) {
        EasyLike likeById = feedBackPostMapper.findLikeById(easyLike.getUserId(), easyLike.getPostId());
        if (likeById == null){   //没有点赞过
             easyLike.setLikeTime(LocalDateTime.now());
             return feedBackPostMapper.toLike(easyLike);
         }
        return 3;
    }

    /*
    * 用户收藏
    * */
    @Override
    public int toCollection(Collection collection) {
         Collection collectionById= feedBackPostMapper.findCollectionById(collection.getUserId(),collection.getPostId());
        if (collectionById == null){   //没有收藏过
            collection.setCollectionTime(LocalDateTime.now());
            return feedBackPostMapper.toCollection(collection);
        }
        return 3;
    }

    @Override
    public int toComment(Comment comment) {
        comment.setCommentTime(LocalDateTime.now());
        return feedBackPostMapper.toComment(comment);
    }
}

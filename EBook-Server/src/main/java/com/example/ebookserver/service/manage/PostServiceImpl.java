package com.example.ebookserver.service.manage;

import com.example.ebookserver.mapper.PostMapper;
import com.example.ebookserver.pojo.*;
import com.example.ebookserver.service.PostService;
import com.example.ebookserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserService userService;


    @Transactional   //事务管理
    @Override
    public int toPost(Post post) {
        int role = postMapper.selectRole(post.getUserId());
        if (role >= 4){   //用户不能发帖
            return 3;
        }else {
            int result = postMapper.post(post);
            if (post.getUrls() != null){
               postMapper.toImages(post.getId(),post.getUrls());
            }
            return result;
        }
    }

    @Override
    public PageBean allPOst(Integer id, Integer page, Integer pageSize) {
        /*
        1.查询使用者拉黑了的用户
        2.查询拉黑了使用者的用户
        3.查询把除上面的使用使用者发布的帖子展示，按点赞量和时间
        */
        List<Integer> users = new ArrayList<>();
        users.addAll(userService.selectBlackUsers(id));
        users.addAll(userService.selectBlackedUsers(id));

        //查询帖子总数
        Long count = postMapper.count(users);
        Integer start = (page -1) * pageSize;
        //分页查询帖子得到的数据
        List<Posts> posts = postMapper.page(users,start,pageSize);
        for (Posts post : posts) {
            //通过帖子id查url集合
            post.setUrl(postMapper.getUrl(post.getId()));
        }
        //返回的类
        PageBean pageBean = new PageBean(count, posts);
        return pageBean;
    }

    @Override
    public PostDetails selectDetails(Integer postId) {
        Posts post = postMapper.selectPost(postId);
        post.setUrl(postMapper.getUrl(postId));
        List<Comments> commentsList= postMapper.selectComments(postId);
        PostDetails postDetails= new PostDetails();
        postDetails.setCommentsNum(postMapper.countComments(postId));
        postDetails.setPosts(post);
        postDetails.setCommentsList(commentsList);
        return postDetails;
    }

    @Override
    public List<Posts> getPosts(Integer id) {
        List<Posts> posts = postMapper.getPostsByUserId(id);
        for (Posts post : posts) {
            post.setUrl(postMapper.getUrl(post.getId()));
        }
        return posts;
    }
}

package com.example.ebookserver.service.manage;

import com.example.ebookserver.mapper.PostMapper;
import com.example.ebookserver.pojo.PageBean;
import com.example.ebookserver.pojo.Post;
import com.example.ebookserver.pojo.PostShow;
import com.example.ebookserver.service.PostService;
import com.example.ebookserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserService userService;
    @Override
    public int toPost(Post post) {
        int role = postMapper.selectRole(post.getUserId());
        if (role >= 4){   //用户不能发帖
            return 3;
        }else {
            int result1 = postMapper.post(post);
            int result2 = postMapper.toImages(post.getId(),post.getUrls());
            return (result1 == 1 && result2 >= 1) ? 1:0;
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
        List<PostShow> postShows = postMapper.page(users,start,pageSize);
        for (PostShow postShow : postShows) {
            //通过帖子id查url集合
            postShow.setUrl(postMapper.getUrl(postShow.getId()));
        }
        //返回的类
        PageBean pageBean = new PageBean(count,postShows);
        return pageBean;
    }
}

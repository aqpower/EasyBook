package com.example.ebookserver.service.manage;

import com.example.ebookserver.mapper.PostMapper;
import com.example.ebookserver.pojo.*;
import com.example.ebookserver.service.PostService;
import com.example.ebookserver.service.UserService;
import com.example.ebookserver.utils.FileUntil;
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
            post.setContentText(FileUntil.filterSensitivityWord(post.getContentText(),'*'));
            post.setTitle(FileUntil.filterSensitivityWord(post.getTitle(),'*'));
            if (post.getLyrics() != null){
               post.setLyrics(FileUntil.filterSensitivityWord(post.getLyrics(),'*'));
            }
            int result = postMapper.post(post);
            if (post.getUrls() != null){
                postMapper.toImages(post.getId(),post.getUrls());
            }
            return result;
        }
    }

    public List<Integer> getBlackList(Integer id){
        List<Integer> users = new ArrayList<>();
        users.addAll(userService.selectBlackUsers(id));
        users.addAll(userService.selectBlackedUsers(id));
        return users;
    }
    public List<Posts> setUrls(List<Posts> posts){
        for (Posts post : posts) {
            //通过帖子id查url集合
            post.setUrl(postMapper.getUrl(post.getId()));
        }
        return posts;
    }
//    @Override
//    public PageBean allPOst(Integer id, Integer page, Integer pageSize) {
//        /*
//        1.查询使用者拉黑了的用户
//        2.查询拉黑了使用者的用户
//        3.查询把除上面的使用使用者发布的帖子展示，按点赞量和时间
//        */
//        List<Integer> users = getBlackList(id);
//
//        //查询帖子总数
//        Long count = postMapper.count(users);
//        Integer start = (page -1) * pageSize;
//        //分页查询帖子得到的数据
//        List<Posts> posts = postMapper.page(users,start,pageSize);
//        posts = setUrls(posts);
//        //返回的类
//        PageBean pageBean = new PageBean((count+pageSize-1)/pageSize, posts);
//        return pageBean;
//    }

    @Override
    public PostDetails selectDetails(Integer postId) {

        Posts post = postMapper.selectPost(postId);
        if (post != null){
            post.setUrl(postMapper.getUrl(postId));
            List<Comments> commentsList= postMapper.selectComments(postId);
            PostDetails postDetails= new PostDetails();
            postDetails.setCommentsNum(postMapper.countComments(postId));
            postDetails.setPosts(post);
            postDetails.setCommentsList(commentsList);
            return postDetails;
        }
        return null;
    }

    @Override
    public PageBean getPosts(Integer id, Integer page, Integer pageSize) {
        Long count = postMapper.countPostsByUserId(id);
        Integer start = (page - 1) * pageSize;
        List<Posts> posts = postMapper.getPostsByUserId(id,start,pageSize);
        for (Posts post : posts) {
            post.setUrl(postMapper.getUrl(post.getId()));
        }
        return new PageBean((count + pageSize - 1) / pageSize, posts);
    }

    @Override
    public void deletePost(Integer id) {
        postMapper.deleteById(id);
    }

    @Override
    public PageBean search(Short color, String text, Integer id, Integer page, Integer pageSize) {
        List<Integer> users = getBlackList(id);

        //查询帖子总数
        Long count = postMapper.count(users);
        Integer start = (page - 1) * pageSize;
        //分页查询帖子得到的数据
        List<Posts> posts = postMapper.pageSearch(color, text, users, start, pageSize);
        posts = setUrls(posts);
        //返回的类
        PageBean pageBean = new PageBean((count + pageSize - 1) / pageSize, posts);
        return pageBean;
    }

    @Override
    public PageBean showCarePosts(Integer id, Integer page, Integer pageSize, List<Integer> careList) {
        if (careList.size() != 0){
            //查询帖子总数
            Long count = postMapper.countCarePosts(careList);
            Integer start = (page - 1) * pageSize;
            //分页查询帖子得到的数据
            List<Posts> posts = postMapper.pageCare(careList, start, pageSize);
            posts = setUrls(posts);
            //返回的类
            return new PageBean((count + pageSize - 1) / pageSize, posts);
        }
        return null;
    }

    @Override
    public PageBean getLikePosts(Integer id, Integer page, Integer pageSize) {
        //查询帖子总数
        Long count = postMapper.countlikePosts(id);
        Integer start = (page - 1) * pageSize;
        //分页查询帖子得到的数据
        List<Posts> posts = postMapper.pageLike(id, start, pageSize);
        posts = setUrls(posts);
        //返回的类
        return new PageBean((count + pageSize - 1) / pageSize, posts);
    }

    @Override
    public PageBean getCollectionPosts(Integer id, Integer page, Integer pageSize) {
        //查询帖子总数
        Long count = postMapper.countCollectionPosts(id);
        Integer start = (page - 1) * pageSize;
        //分页查询帖子得到的数据
        List<Posts> posts = postMapper.pageCollection(id, start, pageSize);
        posts = setUrls(posts);
        //返回的类
        return new PageBean((count + pageSize - 1) / pageSize, posts);
    }
}

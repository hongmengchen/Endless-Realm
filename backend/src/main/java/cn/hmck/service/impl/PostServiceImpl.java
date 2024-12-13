package cn.hmck.service.impl;

import cn.hmck.entity.Post;
import cn.hmck.mapper.CommentMapper;
import cn.hmck.mapper.PostMapper;
import cn.hmck.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 动态服务实现类
 * 实现动态相关的业务逻辑
 *
 * @author 陈亮
 * @since 2024-12-6
*/

@Service("postService")
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    // 注入持久层
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    @Autowired
    public PostServiceImpl(PostMapper postMapper, CommentMapper commentMapper) {
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
    }

    /**
     * 发布动态
     *
     * @param post 动态对象
     * @return 是否发布成功
     */
    @Override
    public boolean publishPost(Post post) {
        return postMapper.insertPost(post) > 0;
    }

    /**
     * 更新动态
     *
     * @param post 动态对象
     * @return 是否更新成功
     */
    @Override
    public boolean updatePost(Post post) {
        // 动态更新字段，仅更新非空的字段
        return postMapper.updatePost(post) > 0;
    }

    /**
     * 删除动态
     *
     * @param id 动态ID
     * @return 是否删除成功
     */
    @Override
    public boolean deletePostById(Integer id) {
        return postMapper.deletePostById(id) > 0;
    }

    /**
     * 根据用户ID获取动态列表
     *
     * @param userId 用户ID
     * @return 动态列表
     */
    @Override
    public List<Post> getPostsByUserId(Integer userId) {
        return postMapper.findPostsByUserId(userId);
    }

    // 获取所有动态
    @Override
    public List<Post> getAllPost() {
        return postMapper.getAllPost();
    }

    @Override
    public Post getPostById(Integer id) {
        return postMapper.getPostById(id);
    }

    // 更新动态统计信息
    @Override
    public void updatePostStatistics(Integer postId) {
        // 统计点赞数和评论数
        int likeCount = commentMapper.countCommentsByPostIdAndType(postId, 2);  // 获取点赞数，type = 2
        int commentCount = commentMapper.countCommentsByPostIdAndType(postId, 1);  // 获取评论数，type = 1

        // 更新 post 表的 likeCount 和 commentCount
        Post post = postMapper.getPostById(Math.toIntExact(postId));
        if (post != null) {
            post.setLikeCount(likeCount);
            post.setCommentCount(commentCount);
            postMapper.updatePost(post);
        }
    }

    // 根据动态状态获取所有动态
    @Override
    public List<Post> getPostByStatus(Integer status) {
        return postMapper.getPostByStatus(status);
    }
}

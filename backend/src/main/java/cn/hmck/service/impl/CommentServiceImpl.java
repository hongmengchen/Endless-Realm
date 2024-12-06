package cn.hmck.service.impl;

import cn.hmck.entity.Comment;
import cn.hmck.mapper.CommentMapper;
import cn.hmck.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论/点赞服务实现类
 * 实现对评论及点赞的增删改查操作
 *
 * @author 陈亮
 * @since 2024-12-6
*/

@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    // 注入持久层
    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    /**
     * 发表评论或点赞
     *
     * @param comment 评论或点赞实体
     * @return 是否成功
     */
    @Override
    public boolean publishComment(Comment comment) {
        return commentMapper.insertComment(comment) > 0;
    }

    /**
     * 修改评论内容
     *
     * @param comment 评论实体，包含ID和新的内容
     * @return 是否成功
     */
    @Override
    public boolean updateCommentContent(Comment comment) {
        return commentMapper.updateCommentContent(comment) > 0;
    }

    /**
     * 查询指定动态的所有评论
     *
     * @param postId 动态ID
     * @return 评论列表
     */
    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentMapper.selectCommentsByPostId(postId);
    }

    /**
     * 删除评论或取消点赞
     *
     * @param id 评论或点赞的ID
     * @return 是否成功
     */
    @Override
    public boolean deleteCommentById(Long id) {
        return commentMapper.deleteCommentById(id) > 0;
    }
}

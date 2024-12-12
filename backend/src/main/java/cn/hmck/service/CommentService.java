package cn.hmck.service;

import cn.hmck.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 评论/点赞服务层
 *
 * @author 陈亮
 * @since 2024-12-6
*/

public interface CommentService extends IService<Comment> {
    /**
     * 发表评论或点赞
     *
     * @param comment 评论或点赞实体
     * @return 是否成功
     */
    Comment publishComment(Comment comment);

    /**
     * 修改评论内容
     *
     * @param comment 评论实体，包含ID和新的内容
     * @return 是否成功
     */
    boolean updateCommentContent(Comment comment);

    /**
     * 根据动态ID查询评论列表
     *
     * @param postId 动态ID
     * @return 评论列表
     */
    List<Comment> getCommentsByPostId(Integer postId);

    /**
     * 删除评论或取消点赞
     *
     * @param id 评论或点赞的ID
     * @return 是否成功
     */
    boolean deleteCommentById(Integer id);
}

package cn.hmck.mapper;

import cn.hmck.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论/点赞持久层
 * 提供对评论/点赞的基本增删改查操作
 *
 * @author 陈亮
 * @since 2024-12-6
*/

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 插入评论或点赞
     *
     * @param comment 评论实体
     * @return 插入的行数
     */
    int insertComment(Comment comment);

    /**
     * 根据动态ID查询评论列表
     *
     * @param postId 动态ID
     * @return 评论列表
     */
    List<Comment> selectCommentsByPostId(@Param("postId") Integer postId);

    /**
     * 修改评论内容
     *
     * @param comment 评论实体，包含ID和新内容
     * @return 更新的行数
     */
    int updateCommentContent(Comment comment);

    /**
     * 根据ID删除评论或点赞
     *
     * @param id 评论ID
     * @return 删除的行数
     */
    int deleteCommentById(@Param("id") Integer id);

    // 统计点赞/评论数
    int countCommentsByPostIdAndType(@Param("postId") Integer postId, @Param("type") Integer type);
}

package cn.hmck.mapper;

import cn.hmck.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 评论/点赞持久层
 *
 * @author 陈亮
 * @since 2024-12-6
*/

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    // 添加评论或者点赞
    int addComment(Comment comment);
    // 根据动态id查询评论
    Map<String, Object> selectCommentByPostId(@Param("postId") Long postId);
    // 修改评论内容
    int updateCommentContent(Comment comment);
    // 删除评论或者点赞
    int deleteById(@Param("id") Long id);
}

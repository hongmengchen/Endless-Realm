package cn.hmck.mapper;

import cn.hmck.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 动态持久层
 *
 * @author 陈亮
 * @since 2024-12-6
*/

@Mapper
public interface PostMapper extends BaseMapper<Post> {
    // 添加动态
    int addPost(Post post);
    // 根据用户id查询动态
    Map<String, Object> selectPostByUserId(@Param("userId") Integer userId);
    // 更新动态内容
    int updatePostContent(Post post);
    // 更新动态点赞
    int updatePostLikeCount(Post post);
    // 更新动态评论
    int updatePostCommentCount(Post post);
    // 更新动态状态
    int updatePostStatus(Post post);
    // 删除动态
    int deleteById(@Param("id") Integer id);
}

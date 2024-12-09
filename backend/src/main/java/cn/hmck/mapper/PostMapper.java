package cn.hmck.mapper;

import cn.hmck.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 动态持久层
 * 提供与 Post 实体相关的数据库操作
 *
 * @author 陈亮
 * @since 2024-12-6
*/

@Mapper
public interface PostMapper extends BaseMapper<Post> {
    /**
     * 添加动态
     * @param post 动态对象
     * @return 受影响的行数
     */
    int insertPost(Post post);

    /**
     * 根据用户ID查询动态列表
     * @param userId 用户ID
     * @return 动态列表
     */
    List<Post> findPostsByUserId(@Param("userId") Integer userId);

    /**
     * 动态更新动态的字段
     * @param post 动态对象
     * @return 受影响的行数
     */
    int updatePost(@Param("post") Post post);

    /**
     * 根据动态ID删除动态
     * @param id 动态ID
     * @return 受影响的行数
     */
    int deletePostById(@Param("id") Integer id);

    // 获取所有动态
    List<Post> getAllPost();
}

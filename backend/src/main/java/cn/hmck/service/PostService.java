package cn.hmck.service;

import cn.hmck.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 动态服务层
 * 定义动态相关的业务接口
 *
 * @author 陈亮
 * @since 2024-12-6
*/

public interface PostService extends IService<Post> {
    /**
     * 发布动态
     *
     * @param post 动态对象
     * @return 是否发布成功
     */
    boolean publishPost(Post post);

    /**
     * 更新动态
     *
     * @param post 动态对象
     * @return 是否更新成功
     */
    boolean updatePost(Post post);

    /**
     * 删除动态
     *
     * @param id 动态ID
     * @return 是否删除成功
     */
    boolean deletePostById(Integer id);

    /**
     * 根据用户ID获取动态列表
     *
     * @param userId 用户ID
     * @return 动态列表
     */
    List<Post> getPostsByUserId(Integer userId);

    // 获取所有动态
    List<Post> getAllPost();

    // 获取动态详情
    Post getPostById(Integer id);

    // 更新动态的点赞数和评论数
    void updatePostStatistics(Integer postId);

    // 根据动态状态获取所有动态
    List<Post> getPostByStatus(Integer status);
}

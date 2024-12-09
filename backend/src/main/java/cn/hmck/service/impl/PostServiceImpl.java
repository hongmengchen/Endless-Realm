package cn.hmck.service.impl;

import cn.hmck.entity.Post;
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

    @Autowired
    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
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
}

package cn.hmck.service.impl;

import cn.hmck.entity.Post;
import cn.hmck.mapper.PostMapper;
import cn.hmck.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 动态服务实现类
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
}

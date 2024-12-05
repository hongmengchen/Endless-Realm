package cn.hmck.service.impl;

import cn.hmck.entity.Comment;
import cn.hmck.mapper.CommentMapper;
import cn.hmck.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 评论/点赞服务实现类
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

}

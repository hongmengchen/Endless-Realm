package cn.hmck.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("er_comment")
public class Comment {
    @TableId(type = IdType.AUTO)
    private Long id;  // 评论ID

    private Long postId;  // 动态ID

    private Long userId;  // 发布评论的用户ID

    private Integer type;  // 评论类型，1为评论，2为点赞

    private String content;  // 评论内容

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;  // 创建时间
}

package cn.hmck.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("er_comment")
public class Comment implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;  // 评论ID

    private Integer postId;  // 动态ID

    private Integer userId;  // 发布评论的用户ID

    private Integer type;  // 评论类型，1为评论，2为点赞

    private String content;  // 评论内容

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;  // 创建时间

    // 序列化版本号
    @Serial
    private static final long serialVersionUID = 1L;
}

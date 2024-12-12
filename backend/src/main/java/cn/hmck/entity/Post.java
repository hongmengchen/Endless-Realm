package cn.hmck.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("er_post")
public class Post implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;  // 动态ID

    private Integer userId;  // 发布动态的用户ID

    private String content;  // 动态内容

    private String mediaUrl;  // 图片或视频的URL

    private Integer likeCount;  // 点赞数

    private Integer commentCount;  // 评论数

    private Integer status;  // 0: 屏蔽, 1: 公开

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;  // 创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;  // 更新时间

    // 序列化版本号
    @Serial
    private static final long serialVersionUID = 1L;
}

package cn.hmck.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("er_notification")
public class Notification {
    @TableId(type = IdType.AUTO)
    private Long id;  // 主键

    private Long userId;  // 用户ID

    private String message;  // 通知内容

    private Integer isRead; // 0: 未读, 1: 已读

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;  // 创建时间
}

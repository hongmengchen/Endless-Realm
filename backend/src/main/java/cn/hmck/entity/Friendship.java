package cn.hmck.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("er_friendship")
public class Friendship {
    @TableId(type = IdType.AUTO)
    private Long id;  // 朋友关系ID

    private Long userId;  // 用户ID

    private Long friendId;  // 朋友ID

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;  // 创建时间
}

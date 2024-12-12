package cn.hmck.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("er_friendship")
public class Friendship implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;  // 朋友关系ID

    private Integer userId;  // 用户ID

    private Integer friendId;  // 朋友ID

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;  // 创建时间

    // 序列化版本号
    @Serial
    private static final long serialVersionUID = 1L;
}

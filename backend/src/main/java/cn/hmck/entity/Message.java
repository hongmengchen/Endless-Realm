package cn.hmck.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("er_message")
public class Message implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;  // 消息ID

    private Integer senderId;  // 发送者ID

    private Integer receiverId;  // 接收者ID

    private String content;  // 消息内容

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;  // 发送时间

    // 序列化版本号
    @Serial
    private static final long serialVersionUID = 1L;
}

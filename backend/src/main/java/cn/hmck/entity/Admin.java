package cn.hmck.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("er_admin")
public class Admin implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;  // 管理员ID

    private String username;  // 管理员用户名

    private String password;  // 管理员密码

    private String role; // 超级管理员/普通管理员

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;  // 创建时间

    // 序列化版本号
    @Serial
    private static final long serialVersionUID = 1L;
}

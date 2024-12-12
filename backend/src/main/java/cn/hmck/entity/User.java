package cn.hmck.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("er_user")
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;  // 用户ID

    private String username;  // 用户名

    private String email;  // 邮箱

    private String password;  // 密码

    private String phone;  // 手机号

    private String avatar;  // 头像

    private Integer status; // 0: 禁用, 1: 启用

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;  // 注册时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;  // 更新时间

    // 序列化版本号
    @Serial
    private static final long serialVersionUID = 1L;
}

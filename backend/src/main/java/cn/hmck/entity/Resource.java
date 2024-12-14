package cn.hmck.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 文件资源实体类
 *
 * @author 陈亮
 * @since 2024-12-14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource implements Serializable {
    private String name;  //name属性表示文件名称

    // 序列化版本号
    @Serial
    private static final long serialVersionUID = 1L;
}

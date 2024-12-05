package cn.hmck.mapper;

import cn.hmck.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 管理员持久层
 *
 * @author 陈亮
 * @since 2024-12-6
*/

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    // 根据用户名和密码查找管理员
    Admin selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    // 查找所有用户
    Map<String, Object> selectAllUser();
}

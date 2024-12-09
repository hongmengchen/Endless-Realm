package cn.hmck.mapper;

import cn.hmck.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员持久层
 *
 * @author 陈亮
 * @since 2024-12-6
*/

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 根据用户名和密码查找管理员
     *
     * @param username 管理员的用户名
     * @param password 管理员的密码
     * @return 查找到的管理员对象
     */
    Admin selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 查询所有管理员
     *
     * @return 所有管理员列表
     */
    List<Admin> selectAllAdmin();

    /**
     * 根据用户名查询管理员
     *
     * @param username 管理员的用户名
     * @return 查找到的管理员对象
     */
    Admin selectByUsername(@Param("username") String username);
}

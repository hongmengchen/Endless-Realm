package cn.hmck.mapper;

import cn.hmck.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户持久层
 * 提供与 User 实体相关的数据库操作
 *
 * @author 陈亮
 * @since 2024-11-30
*/

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 插入用户
     *
     * @param user 用户信息
     * @return 影响行数
     */
    int insertUser(User user);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User selectByUsername(String username);

    /**
     * 根据用户名和密码查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据ID查询用户
     *
     * @param id 用户ID
     * @return 用户信息
     */
    User selectById(Integer id);

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 影响行数
     */
    int updateUser(User user);

    /**
     * 修改用户密码
     *
     * @param id 用户ID
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return 影响行数
     */
    int updateUserPassword(@Param("id") Integer id, @Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 影响行数
     */
    int deleteById(Integer id);

    // 根据账号状态获取所有用户
    List<User> getAllUserByStatus(Integer status);
}

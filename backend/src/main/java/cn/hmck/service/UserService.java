package cn.hmck.service;

import cn.hmck.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户服务层
 * 定义用户相关的业务接口
 *
 * @author 陈亮
 * @since 2024-11-30
*/

public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 注册是否成功
     */
    boolean userSignIn(User user);
    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录成功的用户信息，如果失败则返回 null
     */
    User userLogin(String username, String password);
    /**
     * 根据用户ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    User getUserInfo(Integer id);
    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 更新是否成功
     */
    boolean updateUserInfo(User user);
    /**
     * 修改用户密码
     *
     * @param id 用户ID
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return 修改是否成功
     */
    boolean updateUserPassword(Integer id, String oldPassword, String newPassword);
}

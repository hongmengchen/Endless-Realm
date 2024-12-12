package cn.hmck.service.impl;

import cn.hmck.entity.User;
import cn.hmck.mapper.UserMapper;
import cn.hmck.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务实现类
 * 实现用户相关的业务逻辑
 *
 * @author 陈亮
 * @since 2024-11-30
*/

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    // 注入持久层
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 注册是否成功
     */
    @Override
    public boolean userSignIn(User user) {
        // 检查用户是否已存在
        if (userMapper.selectByUsername(user.getUsername()) != null) {
            return false; // 用户名已存在
        }
        // 插入用户信息
        return userMapper.insertUser(user) > 0;
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录成功的用户信息，如果失败则返回 null
     */
    @Override
    public User userLogin(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
    }

    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
    public User getUserInfo(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 更新是否成功
     */
    @Override
    public boolean updateUserInfo(User user) {
        return userMapper.updateUser(user) > 0;
    }

    /**
     * 修改用户密码
     *
     * @param id 用户ID
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return 修改是否成功
     */
    @Override
    public boolean updateUserPassword(Integer id, String oldPassword, String newPassword) {
        // 确保用户存在并且旧密码正确
        return userMapper.updateUserPassword(id, oldPassword, newPassword) > 0;
    }

    // 根据账号状态获取所有用户
    @Override
    public List<User> getAllUserByStatus(Integer status) {
        return userMapper.getAllUserByStatus(status);
    }
}

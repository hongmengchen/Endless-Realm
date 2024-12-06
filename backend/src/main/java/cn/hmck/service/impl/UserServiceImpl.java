package cn.hmck.service.impl;

import cn.hmck.entity.User;
import cn.hmck.mapper.UserMapper;
import cn.hmck.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    // 注入持久层
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 用户注册
    @Transactional
    @Override
    public boolean userSignIn(User user) {
        // 检查用户账号是否已存在
        if(userMapper.selectByUsername(user.getUsername()) != null){
            return false;
        }else{
            return userMapper.addUser(user) > 0;
        }
    }

    // 用户登录
    @Override
    public User userLogin(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
    }

    // 获取用户信息
    @Override
    public User getUserInfo(Integer id) {
        return userMapper.selectById(id);
    }

    // 更新用户信息
    @Override
    public boolean updateUserInfo(User user) {
        return userMapper.updateUser(user) > 0;
    }

    // 修改用户密码
    @Override
    public boolean updateUserPassword(Integer id, String oldPassword, String newPassword) {
        return userMapper.updateUserPassword(id, oldPassword, newPassword) > 0;
    }
}

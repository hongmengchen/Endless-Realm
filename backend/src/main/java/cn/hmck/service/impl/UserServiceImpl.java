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
    // 注入 UserMapper
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 用户注册
    @Transactional
    @Override
    public boolean register(User user) {
        if(userMapper.selectByUsername(user.getUsername()) != null){
            return false;
        }else{
            return userMapper.addUser(user) > 0;
        }
    }
}

package cn.hmck.service.impl;

import cn.hmck.entity.User;
import cn.hmck.mapper.UserMapper;
import cn.hmck.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}

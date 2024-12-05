package cn.hmck.service;

import cn.hmck.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    // 用户注册
    boolean userSignIn(User user);
    // 用户登录
    User userLogin(String username, String password);
}

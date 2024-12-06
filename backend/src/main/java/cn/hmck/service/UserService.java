package cn.hmck.service;

import cn.hmck.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    // 用户注册
    boolean userSignIn(User user);
    // 用户登录
    User userLogin(String username, String password);
    // 获取用户信息
    User getUserInfo(Integer id);
    // 更新用户信息
    boolean updateUserInfo(User user);
    // 修改用户密码
    boolean updateUserPassword(Integer id, String oldPassword, String newPassword);
}

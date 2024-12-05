package cn.hmck.mapper;

import cn.hmck.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 添加用户
    int addUser(User user);
    // 根据用户名查询用户
    User selectByUsername(@Param("username") String username);
    // 根据用户名和密码查询用户
    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    // 根据id查询用户
    User selectById(@Param("id") Integer id);
}

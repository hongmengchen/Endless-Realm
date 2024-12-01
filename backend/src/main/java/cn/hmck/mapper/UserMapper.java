package cn.hmck.mapper;

import cn.hmck.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 添加用户
    int addUser(User user);
    // 根据用户名查询用户
    User selectByUsername(String username);
}

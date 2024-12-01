package cn.hmck.mapper;

import cn.hmck.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 添加新用户
    int insert(User user);
}

package Test;

import cn.hmck.entity.User;
import cn.hmck.mapper.UserMapper;
import cn.hmck.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class UserTest {
    @Test
    public void addUser() {
        // 获取sqlSession
        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setUsername("hmck");
        user.setPassword("123456");
        user.setEmail("hmck@qq.com");
        user.setPhone("12345678901");
        user.setStatus(1);
        userMapper.insert(user);
    }
}


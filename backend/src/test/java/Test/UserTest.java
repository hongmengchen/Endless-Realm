package Test;

import cn.hmck.entity.User;
import cn.hmck.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    @Test
    public void addUser() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);

        User user = new User();
        user.setUsername("hmck");
        user.setPassword("123456");
        user.setEmail("hmck@qq.com");
        user.setPhone("12345678901");
        user.setStatus(1);
        userMapper.addUser(user);
    }
}


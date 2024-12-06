package Test.Service;

import cn.hmck.entity.User;
import cn.hmck.service.AdminService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class AdminServiceTest {
    @Resource(name = "adminService")
    private AdminService adminService;

    // 查找所有用户
    @Test
    public void selectAllUser() {
        Map<String, Object> userList = adminService.getAllUser();
        for (String key : userList.keySet()){
            System.out.println(key + ":" + userList.get(key));
        }
    }

}

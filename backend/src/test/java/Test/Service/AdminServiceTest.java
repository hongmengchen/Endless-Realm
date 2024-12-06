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

}

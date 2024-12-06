package cn.hmck.service.impl;

import cn.hmck.entity.Admin;
import cn.hmck.mapper.AdminMapper;
import cn.hmck.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员服务实现类
 *
 * @author 陈亮
 * @since 2024-12-6
*/

@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    // 注入持久层
    private final AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    // 管理员登录
    @Override
    public Admin adminLogin(String username, String password) {
        return adminMapper.selectByUsernameAndPassword(username, password);
    }

    // 查找所有用户
    @Override
    public List<Admin> getAllUser() {
        return adminMapper.selectAllUser();
    }
}

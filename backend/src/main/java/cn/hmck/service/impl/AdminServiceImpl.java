package cn.hmck.service.impl;

import cn.hmck.entity.Admin;
import cn.hmck.mapper.AdminMapper;
import cn.hmck.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}

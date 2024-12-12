package cn.hmck.service;

import cn.hmck.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 管理员服务层
 *
 * @author 陈亮
 * @since 2024-12-6
*/

public interface AdminService extends IService<Admin> {
    // 管理员登录
    Admin adminLogin(String username, String password);
    // 查找所有管理员
    List<Admin> getAllAdmin();
}

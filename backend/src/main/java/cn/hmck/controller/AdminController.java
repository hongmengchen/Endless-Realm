package cn.hmck.controller;

import cn.hmck.entity.Admin;
import cn.hmck.service.AdminService;
import cn.hmck.util.ErrorMsg;
import cn.hmck.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 管理员控制器
 *
 * @author 陈亮
 * @since 2024-12-8
*/

@RestController
@RequestMapping("/admin")
public class AdminController {

    // 注入 AdminService
    @Resource(name = "adminService")
    private AdminService adminService;

    // 管理员登录
    @CrossOrigin(origins = "http://localhost:8081", methods = {RequestMethod.POST, RequestMethod.GET})
    @RequestMapping("/login")
    public Result<Admin> login(@RequestParam("username") @NotEmpty @NotNull String username,
                               @RequestParam("password") @NotEmpty @NotNull String password,
                               HttpServletResponse response) {
        Admin admin = adminService.adminLogin(username, password);

        System.out.println("管理员登录：" + admin);

        System.out.println("===================================");
        System.out.println(username + "   " + password);
        System.out.println("===================================");

        if (admin == null) {
            return Result.fail(ErrorMsg.EMAIL_LOGIN_ERROR);
        }

        // 用户名或者密码为空
        if (username.isEmpty() || password.isEmpty()) {
            return Result.fail(ErrorMsg.EMAIL_LOGIN_ERROR);
        }

        Cookie cookie = new Cookie("shAdminId", String.valueOf(admin.getId()));
        cookie.setMaxAge(60*60);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        response.addCookie(cookie);

        return Result.success(admin);
    }
}

package cn.hmck.controller;

import cn.hmck.entity.User;
import cn.hmck.service.UserService;
import cn.hmck.util.ErrorMsg;
import cn.hmck.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 用户控制器
 *
 * @author 陈亮
 * @since 2024-11-30
*/

@RestController
@RequestMapping("/user")
public class UserController {

    // 注入 UserService
    @Resource(name = "userService")
    private UserService userService;

    // 用户注册
    /*@CrossOrigin(origins = "http://localhost:8081", methods = {RequestMethod.POST})*/
    @PostMapping("/register")
    public Result<User> signIn(@RequestBody User user) {
        System.out.println("===================================");
        System.out.println("用户注册：" + user);
        System.out.println("===================================");
        if (userService.userSignIn(user)) {
            return Result.success(user);
        }
        return Result.fail(ErrorMsg.REGISTER_ERROR);
    }

    // 用户登录
    @GetMapping("/login")
    public Result<User> login(@RequestParam("username") @NotEmpty @NotNull String username,
                              @RequestParam("password") @NotEmpty @NotNull String password,
                              HttpServletResponse response) {
        User user = userService.userLogin(username, password);

        System.out.println("用户登录：" + user);

        System.out.println("===================================");
        System.out.println(username + "   " + password);
        System.out.println("===================================");

        if (user == null) {
            return Result.fail(ErrorMsg.EMAIL_LOGIN_ERROR);
        }

        // 用户名或者密码为空
        if(username.isEmpty() || password.isEmpty()){
            return Result.fail(ErrorMsg.EMAIL_LOGIN_ERROR);
        }

        Cookie cookie = new Cookie("shUserId", String.valueOf(user.getId()));
        cookie.setMaxAge(60*60);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        response.addCookie(cookie);

        return Result.success(user);
    }

    // 获取用户信息
    @GetMapping("/getUserInfo")
    public Result<User> getUserInfo(@RequestParam("id") Integer id) {
        User user = userService.getUserInfo(id);
        return Result.success(user);
    }
}

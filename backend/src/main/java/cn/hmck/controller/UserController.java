package cn.hmck.controller;

import cn.hmck.entity.User;
import cn.hmck.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    // 注入 UserService
    @Resource(name = "userService")
    private UserService userService;

    // 用户注册
    @CrossOrigin(origins = "http://localhost:8081", methods = {RequestMethod.POST})
    @PostMapping("/register")
    public String signIn(@RequestBody User user) {
        System.out.println(user);
        boolean result = userService.userSignIn(user);
        System.out.println(result);
        return result ? "success" : "fail";
    }

    // 用户登录
    @CrossOrigin(origins = "http://localhost:8081", methods = {RequestMethod.POST, RequestMethod.GET})
    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletResponse response) {
        User user = userService.userLogin(username, password);

        if (user == null) {
            return "fail";
        }

        System.out.println("登录：" + user);

        System.out.println("===================================");
        System.out.println(username + "   " + password);
        System.out.println("===================================");

        Cookie cookie = new Cookie("shUserId", String.valueOf(user.getId()));
        cookie.setMaxAge(60);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        response.addCookie(cookie);

        // 登录成功，返回成功信息或重定向到主页
        return "success";
    }
}

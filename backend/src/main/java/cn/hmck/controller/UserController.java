package cn.hmck.controller;

import cn.hmck.entity.User;
import cn.hmck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    /*// 注入 UserService
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }*/

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

/*
    // 用户注册
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        // 调用 UserService 的 register 方法进行用户注册
        boolean result = userService.register(user);
        // 根据注册结果返回不同的页面
        return result ? "redirect:/login" : "redirect:/register";
    }*/
}

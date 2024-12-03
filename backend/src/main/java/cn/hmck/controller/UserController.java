package cn.hmck.controller;

import cn.hmck.entity.User;
import cn.hmck.service.UserService;
import cn.hmck.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    // 注入 UserService
    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping("/login")
    public void login() {
        System.out.println("UserController login");
    }

    // 用户注册
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        // 调用 UserService 的 register 方法进行用户注册
        boolean result = userService.register(user);
        // 根据注册结果返回不同的页面
        return result ? "redirect:/login" : "redirect:/register";
    }
}

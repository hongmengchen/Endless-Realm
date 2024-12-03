package cn.hmck.controller;

import cn.hmck.entity.User;
import cn.hmck.service.UserService;
import org.springframework.web.bind.annotation.*;

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
    @CrossOrigin(origins = "http://localhost:8081", methods = {RequestMethod.POST})
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        System.out.println(user);
        boolean result = userService.register(user);
        System.out.println(result);
        return result ? "success" : "fail";
    }
}

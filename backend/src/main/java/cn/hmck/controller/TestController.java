package cn.hmck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

import cn.hmck.entity.User;
import cn.hmck.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    // 注入 UserService
    @Resource(name = "userService")
    private UserService userService;

    // 打开测试页面
    @RequestMapping("/hi")
    public String hi() {
        System.out.println("TestController hi()");
        return "test";
    }

    // 显示所有用户
    @GetMapping("/getAllUser")
    @ResponseBody
    public List<User> getAllUser() {
        // 获取所有状态为 1 的用户
        List<User> users = userService.getAllUserByStatus(1);
        return users; // 返回 JSON 数据
    }
}
   
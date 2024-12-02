package cn.hmck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    //设定当前方法的访问映射地址
    @RequestMapping("/first")
    //设置当前方法返回值类型为 String，用于指定请求完成后跳转的页面
    public String sayHello() {
        System.out.println("访问到 FirstController!");
        //设定具体跳转的页面
        return "success";
    }
}

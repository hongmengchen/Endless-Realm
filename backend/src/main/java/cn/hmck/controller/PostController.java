package cn.hmck.controller;

import cn.hmck.entity.Post;
import cn.hmck.service.PostService;
import cn.hmck.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 动态控制器
 *
 * @author 陈亮
 * @since 2024-12-8
*/

@RestController
@RequestMapping("/post")
public class PostController {
    // 注入 PostService
    @Resource(name = "postService")
    private PostService postService;

    // 获取当前用户所有动态
    @RequestMapping("/getPostsByUserId")
    public Result<List<Post>> getPostsByUserId(@RequestParam("userId") Integer userId) {
        return Result.success(postService.getPostsByUserId(userId));
    }
}

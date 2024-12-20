package cn.hmck.controller;

import cn.hmck.entity.Post;
import cn.hmck.service.PostService;
import cn.hmck.util.ErrorMsg;
import cn.hmck.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/getPostByUserId")
    public Result<List<Post>> getPostByUserId(@RequestParam("id") Integer userId) {
        return Result.success(postService.getPostsByUserId(userId));
    }

    // 获取所有动态
    @GetMapping("/getAllPost")
    public Result<List<Post>> getAllPost() {
        return Result.success(postService.getAllPost());
    }

    // 发布动态
    @PostMapping("/publishPost")
    public Result<Post> publishPost(@RequestBody Post post) {
        System.out.println("===================================");
        System.out.println("发布动态：" + post);
        System.out.println("===================================");
        if (postService.publishPost(post)) {
            return Result.success(post);
        }else {
            return Result.fail(ErrorMsg.PUBLISH_POST_ERROR);
        }
    }
    // 获取动态详情
    @GetMapping("/getPostById")
    public Result<Post> getPostById(@RequestParam("id") Integer id) {
        return Result.success(postService.getPostById(id));
    }

    // 根据动态状态获取所有动态
    @GetMapping("/getPostByStatus")
    public Result<List<Post>> getPostByStatus(@RequestParam("status") Integer status) {
        return Result.success(postService.getPostByStatus(status));
    }

    // 更新动态状态
    @PostMapping("/updatePostStatus")
    public Result<?> updatePostStatus(@RequestBody Map<String, Object> map) {
        Integer id = (Integer) map.get("id");
        Integer status = (Integer) map.get("status");

        System.out.println("修改动态状态：" + id + "   " + status);

        if (id == null || status == null) {
            return Result.fail(ErrorMsg.PARAM_ERROR);
        }
        if (postService.updatePostStatus(id, status)) {
            return Result.success();
        }
        return Result.fail(ErrorMsg.UPDATE_ERROR);
    }

    // 更新动态内容
    @PostMapping("/updatePost")
    public Result<?> updatePost(@RequestBody Post post) {
        System.out.println("修改动态：" + post);
        if (postService.updatePost(post)) {
            return Result.success();
        }
        return Result.fail(ErrorMsg.UPDATE_ERROR);
    }

    // 删除动态
    @DeleteMapping("/deletePostById")
    public Result<?> deletePostById(@RequestParam("id") Integer id ) {
        System.out.println("删除动态：" + id);
        if (id == null) {
            return Result.fail(ErrorMsg.PARAM_ERROR);
        }
        if (postService.deletePostById(id)) {
            return Result.success();
        }
        return Result.fail(ErrorMsg.DELETE_ERROR);
    }
}

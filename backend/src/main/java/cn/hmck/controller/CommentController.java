package cn.hmck.controller;

import cn.hmck.entity.Comment;
import cn.hmck.service.CommentService;
import cn.hmck.util.ErrorMsg;
import cn.hmck.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论控制器
 *
 * @author 陈亮
 * @since 2024-12-9
*/

@RestController
@RequestMapping("/comment")
public class CommentController {
    // 注入 CommentService
    @Resource(name = "commentService")
    private CommentService commentService;

    /**
     * 获取评论列表
     *
     * @param postId 文章id
     * @return 评论列表
     */
    @GetMapping("/getCommentByPostId")
    public Result<List<Comment>> getCommentsByPostId(@RequestParam("id") Long postId) {
        System.out.println("获取评论列表：" + postId);
        System.out.println("===================================");
        if(postId == null){
            return Result.fail(ErrorMsg.PARAM_ERROR);
        }
        return Result.success(commentService.getCommentsByPostId(postId));
    }
}

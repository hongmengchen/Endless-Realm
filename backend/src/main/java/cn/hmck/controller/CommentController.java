package cn.hmck.controller;

import cn.hmck.entity.Comment;
import cn.hmck.service.CommentService;
import cn.hmck.service.PostService;
import cn.hmck.util.ErrorMsg;
import cn.hmck.util.Result;
import org.springframework.web.bind.annotation.*;

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

    @Resource(name = "postService")
    private PostService postService;

    /**
     * 获取评论列表
     *
     * @param postId 文章id
     * @return 评论列表
     */
    @GetMapping("/getCommentByPostId")
    public Result<List<Comment>> getCommentsByPostId(@RequestParam("id") Integer postId) {
        System.out.println("===================================");
        System.out.println("获取评论列表：" + postId);
        System.out.println("===================================");
        if(postId == null){
            return Result.fail(ErrorMsg.PARAM_ERROR);
        }
        return Result.success(commentService.getCommentsByPostId(postId));
    }

    // 点赞/评论
    @PostMapping("/likeComment")
    public Result<Comment> like(@RequestBody Comment comment) {
        System.out.println("===================================");
        System.out.println("点赞/评论：" + comment);
        System.out.println("===================================");
        if(comment == null){
            return Result.fail(ErrorMsg.PARAM_ERROR);
        }else {
            Comment newComment = commentService.publishComment(comment);
            // 更新动态统计数据
            postService.updatePostStatistics(comment.getPostId());
            return Result.success(newComment);
        }
    }

    // 删除评论
    @DeleteMapping("/deleteComment")
    public Result<?> deleteComment(@RequestParam("id") Integer id) {
        System.out.println("===================================");
        System.out.println("删除评论：" + id);
        System.out.println("===================================");
        if(id == null){
            return Result.fail(ErrorMsg.PARAM_ERROR);
        }else {
            boolean result = commentService.deleteCommentById(id);
            return result ? Result.success() : Result.fail(ErrorMsg.DELETE_ERROR);
        }
    }
}

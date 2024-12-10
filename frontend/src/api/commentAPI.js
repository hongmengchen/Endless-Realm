import { get } from "@/utils/request";
import { post } from "@/utils/request";

export default class commentAPI {
  // 获取当前动态的所有评论
  static async getAllComment(id) {
    return get("/comment/getCommentByPostId", { id: id });
  }

  // 点赞评论当前动态
  static async likeComment(comment) {
    return post("/comment/likeComment", comment);
  }
}

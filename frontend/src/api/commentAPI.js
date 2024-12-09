import { get } from "@/utils/request";

export default class commentAPI {
  // 获取当前动态的所有评论
  static async getAllComment(id) {
    return get("/comment/getCommentByPostId", { id: id });
  }
}

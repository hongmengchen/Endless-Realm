import { get } from "@/utils/request";
import { post } from "@/utils/request";

export default class postAPI {
  // 获取用户动态
  static async getPostByUserId(id) {
    return get("/post/getPostByUserId", {
      id: id,
    });
  }
  // 获取所有动态
  static async getAllPost() {
    return get("/post/getAllPost");
  }
  // 发布动态
  static async addPost(userPost) {
    return post("/post/publishPost", userPost);
  }
}

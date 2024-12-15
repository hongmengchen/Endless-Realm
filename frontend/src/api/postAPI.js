import { _delete, get, post } from "@/utils/request";

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
  // 获取动态详情
  static async getPostDetail(id) {
    return get("/post/getPostById", {
      id: id,
    });
  }
  // 根据动态状态获取所有动态
  static async getPostByStatus(status) {
    return get("/post/getPostByStatus", {
      status: status,
    });
  }
  // 更新动态状态
  static async updatePostStatus(id, status) {
    return post("/post/updatePostStatus", id, status);
  }
  // 更新动态内容
  static async updatePost(newPost) {
    return post("/post/updatePost", newPost);
  }
  // 删除动态
  static async deletePost(id) {
    return _delete("/post/deletePostById", {
      id: id,
    });
  }
  // 上传动态照片
  static async uploadPostPhoto(file) {
    return post("/file/upload", file);
  }
}

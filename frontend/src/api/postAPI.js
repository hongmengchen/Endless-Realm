import { get } from "@/utils/request";

export default class postAPI {
  static async getPostList(page, limit, sort, order, search) {
    return get("/post/getPostList", {
      page: page,
      limit: limit,
      sort: sort,
      order: order,
      search: search,
    });
  }
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
  // 修改动态
  static async updatePost(id, title, content, cover, tags, status) {
    return get("/post/updatePost", {
      id: id,
      title: title,
      content: content,
      cover: cover,
      tags: tags,
      status: status,
    });
  }
  // 删除动态
  static async deletePost(id) {
    return get("/post/deletePost", {
      id: id,
    });
  }
}

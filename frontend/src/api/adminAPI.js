import { get } from "@/utils/request";

export default class AdminAPI {
  // 管理员登录
  static async login(username, password) {
    return get("/admin/login", {
      username: username,
      password: password,
    });
  }
  // 获取所有管理员
  static async getAllAdmin() {
    return get("/admin/getAllAdmin");
  }
  // 根据账号状态获取所有用户
  static async getAllUser(status) {
    return get("/admin/getAllUserByStatus", {
      status: status,
    });
  }
}

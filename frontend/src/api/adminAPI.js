import { get } from "@/utils/request";

export default class AdminAPI {
  /**
   * 登录
   * @param {String} username 用户名
   * @param {String} password 密码
   * @returns
   */
  static async login(username, password) {
    return get("/admin/login", {
      username: username,
      password: password,
    });
  }
}

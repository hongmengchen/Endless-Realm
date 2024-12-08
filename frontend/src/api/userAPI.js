import { get } from "@/utils/request";

export default class userAPI {
  /**
   * 登录
   * @param {String} username 用户名
   * @param {String} password 密码
   * @returns
   */
  static async login(username, password) {
    return get("/user/login", {
      username: username,
      password: password,
    });
  }
  /**
   * 获取用户信息
   * @param {String} id
   * @returns
   */
  static async getUserInfo(id) {
    return get("/user/getUserInfo", {
      id: id,
    });
  }
}

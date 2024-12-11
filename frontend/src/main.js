// 引入 Vue
import { createApp } from "vue";
// 引入 App.vue
import App from "./App.vue";
// 引入 ElementPlus
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
// 引入路由
import Router from "./router";
// 引入状态管理
import Store from "./store";
// 引入babel-polyfill
import "babel-polyfill";
// 引入容器样式
import "@/common/css/container.css";

// 引入你的 API 方法（假设是某个 API 文件中的函数）
import userAPI from "@/api/userAPI";

// 创建 Vue 实例
const app = createApp(App);

// 添加路由守卫
Router.beforeEach((to, from, next) => {
  document.title = `${to.meta.title || "默认标题"}`; // 设置页面标题

  // 获取 Vuex 中的用户信息
  const username =
    app.config.globalProperties.$store.getters["user/userInfo"]?.username;

  // 如果没有用户信息，且要访问需要登录的页面
  if (
    !username &&
    (to.path === "/profile" ||
      to.path === "/post" ||
      to.path === "/post-detail")
  ) {
    userAPI
      .getUserInfo()
      .then((res) => {
        console.log("getUserInfo:", res);
        if (res.status_code !== 1) {
          // 如果获取用户信息失败，跳转到登录页面
          next("/login");
        } else {
          // 将用户信息保存到 Vuex 中
          app.config.globalProperties.$store.dispatch(
            "user/updateUserInfo",
            res.data
          );
          next(); // 继续路由跳转
        }
      })
      .catch(() => {
        // 请求失败，跳转到登录页面
        next("/login");
      });
  } else {
    // 如果有用户信息或访问不需要登录的页面，继续跳转
    next();
  }
});

// 挂载 App.vue
app.use(Router).use(Store).use(ElementPlus).mount("#app");

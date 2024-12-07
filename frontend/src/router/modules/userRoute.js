// 导出一个路由数组
export default [
  // 定义登录页面的路由
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/login.vue"),
  },
  // 定义注册页面的路由
  {
    path: "/register",
    name: "register",
    component: () => import("@/views/userRegister.vue"),
  },
  // 定义个人资料页面的路由
  {
    path: "/profile",
    name: "profile",
    component: () => import("@/views/profile.vue"),
  },
];

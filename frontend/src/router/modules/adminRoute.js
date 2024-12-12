export default [
  {
    path: "/login-admin",
    name: "login-admin",
    component: () => import("@/views/login-admin.vue"),
    meta: {
      title: "后台登录 | Endless Realm · 无垠之界",
    },
  },
  {
    path: "/admin-platform",
    name: "admin-platform",
    component: () => import("@/views/admin-platform.vue"),
    meta: {
      title: "后台管理 | Endless Realm · 无垠之界",
    },
  },
];

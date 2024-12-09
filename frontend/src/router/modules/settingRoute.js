export default [
  // 设置页面
  {
    path: "/setting",
    name: "setting",
    component: () => import("@/views/setting.vue"),
    meta: {
      title: "设置 | Endless Realm · 无垠之界",
    },
  },
];

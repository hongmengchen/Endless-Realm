export default [
  // 动态页面
  {
    path: "/post",
    name: "post",
    component: () => import("@/views/post.vue"),
    meta: {
      title: "动态 | Endless Realm · 无垠之界",
    },
  },
];

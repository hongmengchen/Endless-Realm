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
  // 动态详情
  {
    path: "/post/:postId",
    name: "postDetail",
    component: () => import("@/views/post-detail.vue"),
    meta: {
      title: "动态详情 | Endless Realm · 无垠之界",
    },
  },
];

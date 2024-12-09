export default [
  // 搜索页面
  {
    path: "/search",
    name: "search",
    component: () => import("@/views/search.vue"),
    meta: {
      title: "搜索 | Endless Realm · 无垠之界",
    },
  },
];

export default [
  // 通知页面
  {
    path: "/notification",
    name: "notification",
    component: () => import("@/views/notification.vue"),
    meta: {
      title: "通知 | Endless Realm · 无垠之界",
    },
  },
];

export default [
  // 消息页面
  {
    path: "/message",
    name: "message",
    component: () => import("@/views/message.vue"),
    meta: {
      title: "消息 | Endless Realm · 无垠之界",
    },
  },
];

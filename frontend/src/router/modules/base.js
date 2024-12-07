// 导出一个默认的数组，数组中包含一个对象
export default [
  {
    path: "/",
    name: "home",
    component: () => import("@/views/index.vue"),
  },
];

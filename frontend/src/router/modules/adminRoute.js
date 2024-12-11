export default [
  {
    path: "/login-admin",
    name: "login-admin",
    component: () => import("@/views/login-admin.vue"),
  },
  {
    path: "/admin-platform",
    name: "admin-platform",
    component: () => import("@/views/admin-platform.vue"),
  },
];

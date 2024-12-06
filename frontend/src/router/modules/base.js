export default [
  {
    path: "/",
    name: "home",
    component: () => import("@/views/index.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/login.vue"),
  },
  {
    path: "/register",
    name: "register",
    component: () => import("@/views/userRegister.vue"),
  },
  {
    path: "/profile",
    name: "profile",
    component: () => import("@/views/profile.vue"),
  },
];

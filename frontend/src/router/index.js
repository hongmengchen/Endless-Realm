import { createRouter, createWebHistory } from "vue-router";

import baseRouters from "@/router/modules/base";
import adminRoute from "@/router/modules/adminRoute";
import userRoute from "@/router/modules/userRoute";
import postRoute from "@/router/modules/postRoute";
import searchRoute from "@/router/modules/searchRoute";
import messageRoute from "@/router/modules/messageRoute";
import notificationRoute from "@/router/modules/notificationRoute";
import settingRoute from "@/router/modules/settingRoute";

const routes = [
  ...baseRouters,
  ...adminRoute,
  ...userRoute,
  ...postRoute,
  ...searchRoute,
  ...messageRoute,
  ...notificationRoute,
  ...settingRoute,
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  scrollBehavior() {
    return {
      el: "#app",
      top: 0,
      behavior: "smooth",
    };
  },
});

export default router;

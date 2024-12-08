import { createRouter, createWebHistory } from "vue-router";

import baseRouters from "./modules/base";
import adminRoute from "./modules/adminRoute";
import userRoute from "./modules/userRoute";
import postRoute from "./modules/postRoute";

const routes = [...baseRouters, ...adminRoute, ...userRoute, ...postRoute];

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

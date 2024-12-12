import { createStore, createLogger } from "vuex";
import userStore from "./modules/userStore";
import adminRoute from "./modules/adminStore";

const debug = process.env.NODE_ENV !== "production";

export default createStore({
  modules: {
    user: userStore, // 注册用户模块
    admin: adminRoute, // 注册管理员模块
  },
  strict: debug,
  plugins: debug ? [createLogger()] : [],
});

import { createStore, createLogger } from "vuex";
import userStore from "./modules/userStore";

const debug = process.env.NODE_ENV !== "production";

export default createStore({
  modules: {
    user: userStore, // 注册用户模块
  },
  strict: debug,
  plugins: debug ? [createLogger()] : [],
});

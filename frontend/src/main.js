// 引入 Vue
import { createApp } from "vue";
// 引入 App.vue
import App from "./App.vue";
// 引入 ElementPlus
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
// 引入路由
import Router from "./router";
// 引入状态管理
import Store from "./store";
// 引入babel-polyfill
import "babel-polyfill";

// 引入容器样式
import "@/common/css/container.css";

// 创建 Vue 实例
const app = createApp(App);

// 挂载 App.vue
app.use(Router).use(Store).use(ElementPlus).mount("#app");

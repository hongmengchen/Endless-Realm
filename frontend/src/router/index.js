//  引入vue-router
import {createRouter, createWebHashHistory} from 'vue-router';
//  引入组件
import Home from '../components/page/index.vue';
import LoginPage from '../components/page/login.vue';

//  定义路由
const routes = [
  {
      path: "/", component: Home
  },
  {
    path: "/login",
    name: "login",
    component: LoginPage
  },
]

//  创建路由
const router = createRouter({
  history: createWebHashHistory(),
  routes: routes,
})
//  导出路由----去main.js导入
export default router

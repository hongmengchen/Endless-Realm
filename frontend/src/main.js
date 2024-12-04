// 引入 Vue
import {createApp} from 'vue'
// 引入 App.vue
import App from './App.vue'
//引入 ElementPlus
import ElementPlus from 'element-plus'
import 'element-plus/theme-chalk/index.css'
// 引入路由
import router from './router'
// 引入babel-polyfill
import 'babel-polyfill';
// 引入axios
import api from './api'

// 创建 Vue 实例
const app = createApp(App)

app.config.globalProperties.$api = api;

// 全局变量
/*let globalData = {
   userInfo: {
       username: ''
   }
};
let sta = {
   isLogin: false,
   adminName: ''
};
Vue.prototype.$sta = sta;
Vue.prototype.$globalData = globalData;*/

// ElementPlus 设置默认的组件大小为 medium
app.use(ElementPlus, {
   size: 'medium'
});

// 路由导航守卫
/*router.beforeEach((to, from, next) => {
    document.title = `${to.meta.title}`;
    const username = app.config.globalProperties.$globalData?.userInfo?.username;
    if (!username
        && (to.path === '/me'
        || to.path === '/comment'
        || to.path === '/release'
        || to.path === '/order')) {
        app.config.globalProperties.$api.getUserInfo().then(res => {
            console.log('getUserInfo:', res);
            if (res.status_code !== 1) {
                next('/login');
            } else {
                res.data.signInTime = res.data.signInTime.substring(0, 10);
                app.config.globalProperties.$globalData.userInfo = res.data;
                next();
            }
        }).catch(e => {
            console.error('Error fetching user info:', e);
            next('/login');
        });
    } else {
        next();
    }
});*/

// 挂载 App.vue
app.use(router).mount('#app');

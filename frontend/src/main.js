//
import { createApp } from 'vue'
//引入 ElementPlus
import ElementPlus from 'element-plus'
// import 'element-plus/theme-chalk/index.css'
// 引入 App.vue
import App from './App.vue'
// 引入axios
import axios from '@/api'
// 引入路由
import router from '@/router'
//引入 全局css
import '@/assets/css/global.css'

createApp(App).use(router).use(axios).use(ElementPlus).mount('#app')
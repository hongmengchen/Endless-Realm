<template>
  <div class="app-container">
    <el-aside class="container-aside" width="collapse">
      <!-- 侧边栏 -->
      <AppSideBar />
    </el-aside>

    <el-main class="container-main">
      <!-- 显示当前路由页面 -->
      <router-view></router-view>
    </el-main>
  </div>
</template>

<script>
import AppSideBar from "@/components/AppSideBar.vue";

export default {
  name: "HomeIndex",

  // 引入组件
  components: {
    AppSideBar,
  },
};
</script>

<style scoped>
.app-container {
  display: flex;
  height: 100vh; /* 使容器占满整个视口高度 */
}

.container-aside {
  padding: 20px; /* 可选：设置内边距 */
  background-color: #ded8d8;
}

.container-main {
  flex-grow: 1; /* 使主要内容区域占据剩余空间 */
  padding: 20px; /* 可选：设置内边距 */
}
</style>

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
}

.container-aside {
  padding: 10px; /* 可选：设置内边距 */
}

.container-main {
  padding: 20px; /* 可选：设置内边距 */
}
</style>

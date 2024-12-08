<template>
  <div class="post">
    <!-- 左侧侧边栏 -->
    <el-aside class="post-aside">
      <AppSideBar />
    </el-aside>

    <!-- 右侧内容 -->
    <el-main class="post-body">
      <!-- 顶部导航栏 -->
    </el-main>
  </div>
</template>
<script>
import AppSideBar from "@/components/AppSideBar.vue";

export default {
  name: "userPostPage",
  components: { AppSideBar },
};
</script>
<style scoped>
.post {
  display: flex; /* 使用 Flexbox 布局 */
  background-color: #f9f9f9; /* 背景色 */
  height: 100vh; /* 全屏高度 */
}
/* 左侧侧边栏样式 */
.post-aside {
  padding: 10px;
  border-right: 1px solid #ddd; /* 分隔线 */
}
/* 主内容区域样式 */
.post-body {
  flex: 1;
  padding: 40px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>

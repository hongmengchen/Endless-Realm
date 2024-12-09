<template>
  <div class="container">
    <!-- 左侧侧边栏 -->
    <el-aside class="container-aside">
      <AppSideBar />
    </el-aside>

    <!-- 右侧内容 -->
    <el-main class="container-body">通知页面</el-main>
  </div>
</template>

<script>
import AppSideBar from "@/components/AppSideBar.vue";

export default {
  name: "notificationPage",
  components: { AppSideBar },
};
</script>

<style scoped></style>

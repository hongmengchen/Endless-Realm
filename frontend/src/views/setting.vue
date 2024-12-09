<template>
  <div class="container">
    <!-- 左侧侧边栏 -->
    <el-aside class="container-aside">
      <AppSideBar />
    </el-aside>

    <!-- 右侧内容 -->
    <el-main class="container-body">设置页面</el-main>
  </div>
</template>

<script>
import AppSideBar from "@/components/AppSideBar.vue";

export default {
  name: "settingPage",
  components: { AppSideBar },
};
</script>

<style></style>

<template>
  <div class="profile">
    <!-- 左侧侧边栏 -->
    <el-aside class="profile-aside">
      <AppSideBar />
    </el-aside>

    <!-- 右侧内容 -->
    <el-main class="profile-body">
      用户名：{{ userInfo.username || "未登录" }}
    </el-main>
  </div>
</template>

<script>
import UserAPI from "@/api/userAPI";
import { mapActions, mapState } from "vuex";
import AppSideBar from "@/components/AppSideBar.vue";

export default {
  name: "userProfilePage",
  components: { AppSideBar },
  computed: {
    ...mapState("user", ["userInfo"]), // 映射 Vuex 的 userInfo 状态
  },
  data() {
    return {};
  },
  created() {
    // 如果用户信息不存在，则尝试加载
    if (!this.userInfo) {
      this.loadUserInfo();
    }
  },
  methods: {
    ...mapActions("user", ["updateUserInfo"]), // 从 Vuex 注册 actions

    async loadUserInfo() {
      try {
        // 获取 Cookie 中的 shUserId
        const shUserId = this.getCookie("shUserId");
        console.log("User ID from cookie:", shUserId);

        // 使用获取到的 shUserId 发送请求获取用户信息
        const res = await UserAPI.getUserInfo(shUserId);
        console.log(res);
      } catch (error) {
        console.error("Error loading user info:", error);
      }
    },

    // 获取 Cookie 的函数
    getCookie(name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);
      if (parts.length === 2) return parts.pop().split(";").shift();
      return null;
    },
  },
};
</script>

<style scoped>
/* 父容器：定义为水平布局 */
.profile {
  display: flex; /* 使用 Flexbox 布局 */
}

/* 左侧侧边栏样式 */
.profile-aside {
  padding: 10px;
}

/* 主内容区域样式 */
.profile-body {
  padding: 20px; /* 可选：内边距 */
}
</style>

<template>
  <div class="profile">
    <!-- 左侧侧边栏 -->
    <el-aside class="profile-aside">
      <AppSideBar />
    </el-aside>

    <!-- 右侧内容 -->
    <el-main class="profile-body">
      <!-- 用户信息展示 -->
      <div v-if="userInfo && userInfo.username" class="profile-container">
        <!-- 头像 -->
        <div class="avatar-section">
          <el-avatar
            :size="100"
            :src="userInfo.avatar || 'https://via.placeholder.com/100'"
            alt="用户头像"
          />
        </div>

        <!-- 用户详细信息 -->
        <div class="info-section">
          <h2>{{ userInfo.username || "未登录" }}</h2>
          <p><strong>邮箱：</strong> {{ userInfo.email || "未绑定" }}</p>
          <p><strong>手机号：</strong> {{ userInfo.phone || "未绑定" }}</p>
          <p>
            <strong>状态：</strong>
            <el-tag :type="userInfo.status === 1 ? 'success' : 'danger'">
              {{ userInfo.status === 1 ? "启用" : "禁用" }}
            </el-tag>
          </p>
          <p>
            <strong>注册时间：</strong>
            {{ formatDate(userInfo.createdAt) || "未知" }}
          </p>
        </div>

        <!-- 操作按钮 -->
        <div class="action-section">
          <el-button type="primary" @click="editProfile">编辑资料</el-button>
          <el-button type="danger" @click="logout">退出登录</el-button>
        </div>
      </div>

      <!-- 如果未登录，显示提示信息 -->
      <div v-else>
        <p>请先登录以查看个人信息。</p>
      </div>
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
    this.loadUserInfo();
  },
  methods: {
    ...mapActions("user", ["updateUserInfo"]), // 从 Vuex 注册 actions

    // 格式化日期
    formatDate(date) {
      // 提取日期时间信息
      const year = date.year;
      const month = String(date.monthValue).padStart(2, "0"); // 补全两位
      const day = String(date.dayOfMonth).padStart(2, "0");
      const hour = String(date.hour).padStart(2, "0");
      const minute = String(date.minute).padStart(2, "0");
      const second = String(date.second).padStart(2, "0");

      // 拼接成标准格式
      return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
    },

    // 编辑资料
    editProfile() {
      console.log("编辑资料");
      // 在这里可以跳转到编辑页面或者打开弹窗
    },

    // 退出登录
    logout() {
      console.log("退出登录");
      document.cookie =
        "shUserId=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
      location.reload();
    },

    // 从后端加载用户信息
    async loadUserInfo() {
      try {
        // 获取 Cookie 中的 shUserId
        const shUserId = this.getCookie("shUserId");
        console.log("User ID from cookie:", shUserId);

        // 使用获取到的 shUserId 发送请求获取用户信息
        const res = await UserAPI.getUserInfo(shUserId);
        console.log(res);
        if (res.data.status_code === 1) {
          // 更新用户信息到 Vuex
          this.updateUserInfo(res.data.data);
          console.log("profile-获取cookie：" + this.userInfo.username); // 打印更新后的用户信息
        }
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
  background-color: #f9f9f9; /* 背景色 */
  height: 100vh; /* 全屏高度 */
}

/* 左侧侧边栏样式 */
.profile-aside {
  padding: 20px;
  border-right: 1px solid #ddd; /* 分隔线 */
}

/* 主内容区域样式 */
.profile-body {
  flex: 1;
  padding: 40px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.profile-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.avatar-section {
  margin-bottom: 20px;
}

.info-section {
  margin-bottom: 30px;
  line-height: 2; /* 提高行距 */
}

.action-section {
  display: flex;
  gap: 10px;
}
</style>

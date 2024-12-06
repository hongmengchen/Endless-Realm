<template>
  <div class="sidebar-container">
    <div class="sidebar-container-logo">
      <img src="../assets/logo.png" alt="logo" class="logo" />
      <span class="title">Endless Realm</span>
    </div>
    <!-- 收缩/扩展按钮 -->
    <div class="sidebar-container-collapse">
      <el-button type="primary" @click="toggleCollapse(false)">
        收缩
      </el-button>
      <el-button type="primary" @click="toggleCollapse(true)"> 扩展 </el-button>
    </div>
    <!-- 侧边菜单 -->
    <div class="sidebar-container-menu">
      <el-menu
        default-active="1"
        class="el-menu"
        :collapse="isCollapse"
        @open="handleOpen"
        @close="handleClose"
        @select="handleSelect"
        background-color="#2c3e50"
        text-color="#ecf0f1"
        active-text-color="#f39c12"
      >
        <el-menu-item index="1">
          <el-icon :style="{ fontSize: iconSize }"><House /></el-icon>
          <template #title>首页</template>
        </el-menu-item>

        <el-menu-item index="2">
          <el-icon :style="{ fontSize: iconSize }"><Aim /></el-icon>
          <template #title>搜索</template>
        </el-menu-item>

        <el-menu-item index="3">
          <el-icon :style="{ fontSize: iconSize }"><PictureRounded /></el-icon>
          <template #title>动态</template>
        </el-menu-item>

        <el-menu-item index="4">
          <el-icon :style="{ fontSize: iconSize }"><ChatLineRound /></el-icon>
          <template #title>消息</template>
        </el-menu-item>

        <el-menu-item index="5">
          <el-icon :style="{ fontSize: iconSize }"><Bell /></el-icon>
          <template #title>通知</template>
        </el-menu-item>

        <el-menu-item index="6">
          <el-icon :style="{ fontSize: iconSize }"><User /></el-icon>
          <template #title>
            <el-dropdown @command="handleCommand">
              <span class="el-dropdown-link"> 我的 </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item command="login">登录</el-dropdown-item>
                  <el-dropdown-item command="register">注册</el-dropdown-item>
                  <el-dropdown-item command="logout">注销</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </template>
        </el-menu-item>

        <el-menu-item index="7">
          <el-icon :style="{ fontSize: iconSize }"><Setting /></el-icon>
          <template #title>设置</template>
        </el-menu-item>
      </el-menu>
    </div>
  </div>
</template>

<script>
import { computed, ref } from "vue";
import { useRouter } from "vue-router";
import {
  House,
  Setting,
  User,
  PictureRounded,
  ChatLineRound,
  Bell,
  Aim,
} from "@element-plus/icons-vue";

export default {
  name: "AppSideBar",

  components: {
    Aim,
    Bell,
    ChatLineRound,
    PictureRounded,
    User,
    House,
    Setting,
  },
  setup() {
    const router = useRouter(); // 使用 Vue Router 进行路由导航
    const isCollapse = ref(false);

    // 动态计算图标大小
    const iconSize = computed(() => (isCollapse.value ? "40px" : "20px"));

    // 切换收缩/扩展状态
    const toggleCollapse = (collapseState) => {
      isCollapse.value = collapseState;
    };

    const handleOpen = (key, keyPath) => {
      console.log(key, keyPath);
    };

    const handleClose = (key, keyPath) => {
      console.log(key, keyPath);
    };

    // 处理菜单项选择，跳转到相应的页面
    const handleSelect = (index) => {
      if (index === "1") {
        router.push("/"); // 首页
      } else if (index === "2") {
        router.push("/friend"); // 搜索
      } else if (index === "3") {
        router.push("/dynamic"); // 动态
      } else if (index === "4") {
        router.push("/message"); // 消息
      } else if (index === "5") {
        router.push("/notification"); // 通知
      } else if (index === "6") {
        router.push("/profile"); // 我的
      } else if (index === "7") {
        router.push("/setting"); // 设置
      }
    };

    // 处理按钮点击事件
    const handleCommand = (command) => {
      if (command === "login") {
        router.push("/login"); // 跳转到登录页面
      } else if (command === "register") {
        router.push("/register"); // 跳转到注册页面
      } else if (command === "logout") {
        router.push("/logout"); // 跳转到注销页面
      }
    };

    return {
      isCollapse,
      iconSize,
      toggleCollapse,
      handleOpen,
      handleClose,
      handleSelect,
      handleCommand,
    };
  },
};
</script>

<style scoped>
/* 主容器布局 */
.sidebar-container-logo {
  display: flex;
  flex-direction: column; /* 垂直排列子元素 */
  justify-content: center; /* 垂直居中 */
  align-items: center; /* 水平居中 */
  height: 100%; /* 确保容器高度占满父容器 */
  margin-bottom: 20px;
}
.logo {
  width: 120px;
}
.title {
  margin-top: 10px; /* 可选：调整 logo 和文字之间的间距 */
  font-size: 20px; /* 可选：调整文字大小 */
  font-family: "华文仿宋", "Orbitron", sans-serif; /* 应用引入的字体 */
}
.sidebar-container-menu {
  display: flex; /* 使用Flexbox布局 */
  flex-direction: column;
  align-items: center;
}
/* 收缩/扩展按钮 */
.sidebar-container-collapse {
  display: flex;
  margin-bottom: 20px;
}
/* 为图标添加过渡效果 */
.el-icon {
  transition: font-size 0.3s ease;
}
.el-dropdown-link {
  color: #f1f1f1;
  outline: none; /* 取消悬浮时的轮廓线 */
}
</style>

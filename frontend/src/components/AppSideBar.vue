<template>
  <div class="sidebar-container">
    <!-- 收缩/扩展按钮 -->
    <div class="sidebar-container-collapse">
      <el-button
        type="primary"
        icon="el-icon-arrow-left"
        @click="toggleCollapse(false)"
      >
        收缩
      </el-button>
      <el-button
        type="primary"
        icon="el-icon-arrow-right"
        @click="toggleCollapse(true)"
      >
        扩展
      </el-button>
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
        <el-menu-item index="1" class="">
          <el-icon><House /></el-icon>
          <template #title>首页</template>
        </el-menu-item>

        <el-menu-item index="2">
          <el-icon><Aim /></el-icon>
          <template #title>搜索</template>
        </el-menu-item>

        <el-menu-item index="3">
          <el-icon><PictureRounded /></el-icon>
          <template #title>动态</template>
        </el-menu-item>

        <el-menu-item index="4">
          <el-icon><ChatLineRound /></el-icon>
          <template #title>消息</template>
        </el-menu-item>

        <el-menu-item index="5">
          <el-icon><Bell /></el-icon>
          <template #title>通知</template>
        </el-menu-item>

        <el-menu-item index="6">
          <el-icon><User /></el-icon>
          <!-- 下拉菜单 -->
          <el-dropdown @command="handleCommand">
            <span class="el-dropdown-link">
              我的
              <i class="el-icon-arrow-down"></i>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <!-- 登录按钮 -->
                <el-dropdown-item command="login"> 登录 </el-dropdown-item>
                <!-- 注册按钮 -->
                <el-dropdown-item command="register"> 注册 </el-dropdown-item>
                <!-- 注销按钮 -->
                <el-dropdown-item command="logout"> 注销 </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </el-menu-item>

        <el-menu-item index="7">
          <el-icon><Setting /></el-icon>
          <template #title>设置</template>
        </el-menu-item>
      </el-menu>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
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
.sidebar-container-menu {
  display: flex; /* 使用Flexbox布局 */
  flex-direction: column;
  align-items: center;
  flex-grow: 1; /* 使菜单项填充剩余空间 */
}
/* 收缩/扩展按钮 */
.sidebar-container-collapse {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}
.el-button {
  font-size: 14px;
  text-align: center;
}
/*菜单项样式*/
.el-menu-item {
  text-align: center; /* 文本居中 */
}
/*菜单项鼠标悬停样式*/
.el-menu-item:hover {
  background-color: #f4c2c2; /* 灰色背景色 */
  border-radius: 10px; /* 圆润边角 */
}
.el-dropdown-menu {
  text-align: center;
  background-color: #696969;
  list-style: none !important;
}
/* 收缩/扩展按钮状态变化 */
.el-menu--collapse {
  width: 80px !important;
  list-style: none !important;
}
.el-menu:not(.el-menu--collapse) {
  width: 60px !important;
  list-style: none !important;
}
</style>

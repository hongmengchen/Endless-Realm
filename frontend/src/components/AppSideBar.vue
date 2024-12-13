<template>
  <div class="sidebar-container" :class="{ 'is-collapsed': isCollapse }">
    <!-- Logo 部分 -->
    <div class="sidebar-container-logo">
      <img src="../assets/logo.png" alt="logo" class="logo" />
      <span class="title" v-if="!isCollapse">Endless Realm</span>
    </div>

    <!-- 收缩/扩展按钮 -->
    <el-button
      type="primary"
      :icon="isCollapse ? Expand : Fold"
      @click="toggleCollapse"
      class="collapse-button"
    >
      {{ isCollapse ? "展开" : "收起" }}
    </el-button>

    <!-- 侧边菜单 -->
    <el-menu
      :default-active="activeIndex"
      class="el-menu-vertical"
      :collapse="isCollapse"
      @select="handleSelect"
      :collapse-transition="false"
      background-color="#linear-gradient(to bottom, #3a7bd5, #00d2ff)"
      text-color="#fff"
      active-text-color="#1abc9c"
    >
      <el-menu-item
        v-for="item in menuItems"
        :key="item.index"
        :index="item.index"
      >
        <el-icon :style="{ fontSize: iconSize }">
          <component :is="item.icon" />
        </el-icon>
        <template #title>
          {{ item.title }}
          <el-dropdown v-if="item.dropdown" @command="handleCommand">
            <span class="el-dropdown-link">
              <el-icon><arrow-down /></el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item
                  v-for="dropItem in item.dropdown"
                  :key="dropItem.command"
                  :command="dropItem.command"
                >
                  {{ dropItem.label }}
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import {
  House,
  Setting,
  User,
  PictureRounded,
  ChatLineRound,
  Bell,
  Aim,
  Expand,
  Fold,
  ArrowDown,
} from "@element-plus/icons-vue";

export default {
  name: "AppSideBar",
  components: {
    ArrowDown,
    Aim,
    Bell,
    ChatLineRound,
    PictureRounded,
    User,
    House,
    Setting,
  },
  computed: {
    Fold() {
      return Fold;
    },
    Expand() {
      return Expand;
    },
    // 动态计算图标大小
    iconSize() {
      return this.isCollapse ? "35px" : "20px";
    },
  },
  data() {
    return {
      isCollapse: false,
      activeIndex: "1",
      expandIcon: Expand,
      foldIcon: Fold,
      menuItems: [
        { index: "1", title: "首页", icon: "House", route: "/" },
        { index: "2", title: "搜索", icon: "Aim", route: "/search" },
        { index: "3", title: "动态", icon: "PictureRounded", route: "/post" },
        { index: "4", title: "消息", icon: "ChatLineRound", route: "/message" },
        { index: "5", title: "通知", icon: "Bell", route: "/notification" },
        {
          index: "6",
          title: "我的",
          icon: "User",
          route: "/profile",
          dropdown: [
            { label: "登录", command: "login" },
            { label: "注册", command: "register" },
            { label: "注销", command: "logout" },
          ],
        },
        { index: "7", title: "设置", icon: "Setting", route: "/setting" },
      ],
    };
  },
  methods: {
    // 切换收缩/扩展状态
    toggleCollapse() {
      this.isCollapse = !this.isCollapse;
    },
    // 处理菜单项选择，跳转到相应的页面
    handleSelect(index) {
      const selectedItem = this.menuItems.find((item) => item.index === index);
      if (selectedItem) {
        this.$router.push(selectedItem.route);
      }
    },
    // 处理下拉菜单命令
    handleCommand(command) {
      switch (command) {
        case "login":
          this.$router.push("/login");
          break;
        case "register":
          this.$router.push("/register");
          break;
        case "logout":
          console.log("退出登录");
          document.cookie =
            "shUserId=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
          location.reload();
          break;
      }
    },
  },
};
</script>

<style scoped>
/* 主容器布局 */
.sidebar-container {
  width: 200px;
  background: linear-gradient(to bottom, #3a7bd5, #00d2ff);
  color: #fff;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px 0;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
  transition: width 0.3s;
}

/* 收起状态的宽度 */
.sidebar-container.is-collapsed {
  width: 64px;
}

/* Logo 部分 */
.sidebar-container-logo {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}

.logo {
  width: 64px;
  height: auto;
  margin-bottom: 10px;
}

.title {
  font-size: 18px;
  font-weight: bold;
  letter-spacing: 1px;
}

/* 收缩/扩展按钮 */
.collapse-button {
  margin-bottom: 20px;
  background-color: transparent;
  border: none;
}

/* 菜单样式 */
.el-menu-vertical:not(.el-menu--collapse) {
  width: 200px;
}

.el-menu-item {
  border-radius: 5px;
  text-align: center;
}

.el-menu-item:hover {
  background: rgba(255, 255, 255, 0.2);
}

.el-dropdown-link {
  color: #fff;
  cursor: pointer;
  margin-left: 5px;
}

/* 适配折叠状态 */
.el-menu--collapse .el-dropdown-link {
  display: none;
}
</style>

<template>
  <div class="admin-platform">
    <el-container>
      <el-header>
        <div class="header">
          <div class="app-name">
            <router-link to="/admin-platform">后台管理</router-link>
          </div>
          <span class="app-title">管理员：{{ adminInfo.username }}</span>
          <div class="app-logOut">
            <el-button style="margin-right: 100px" type="danger" @click="logout"
              >退出登录</el-button
            >
          </div>
        </div>
      </el-header>
      <el-container>
        <div class="mainBody">
          <el-aside>
            <el-col :span="24">
              <el-menu
                default-active="1"
                class="el-menu-vertical-demo"
                @select="handleSelect"
                background-color="#ffffff"
                text-color="#303133"
                active-text-color="#409EFF"
              >
                <el-menu-item index="1">
                  <template #title>用户管理</template>
                </el-menu-item>
                <el-menu-item index="2">
                  <template #title>动态管理</template>
                </el-menu-item>
              </el-menu>
            </el-col>
          </el-aside>
          <el-main>
            <userList v-if="mode == 1"></userList>
            <postList v-if="mode == 2"></postList>
          </el-main>
        </div>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
import userList from "@/common/userList";
import postList from "@/common/postList";

export default {
  name: "admin-platform",
  components: {
    userList,
    postList,
  },
  computed: {
    ...mapState("admin", ["adminInfo"]), // 映射 Vuex 的 adminInfo 状态
  },

  data() {
    return {
      mode: 1,
    };
  },
  created() {},

  methods: {
    // 退出登录
    logout() {
      console.log("退出登录");
      document.cookie =
        "shAdminId=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
      this.$router.push("/");
    },
    // 切换菜单
    handleSelect(val) {
      if (this.mode !== val) {
        this.mode = val;
      }
    },
  },
};
</script>

<style scoped>
.header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  min-width: 100vw;
  height: 58px;
  background: #ffffff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: #eeeeee solid 2px;
  z-index: 1000;
}

.app-name {
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: 10vw;
  flex: 1;
  height: 100%;
  border-right: 1px solid #e5e5e5;
}

.app-name a {
  color: #409eff;
  font-size: 18px;
  font-weight: 800;
  text-decoration: none;
  padding: 0 20px;
}

.app-title {
  display: flex;
  justify-content: center;
  flex: 8;
  width: 100px;
}

.app-logOut {
  display: flex;
  flex: 1;
  justify-content: flex-end;
  align-items: center;
}

.mainBody {
  display: flex;
  width: 100%;
}

aside {
  flex: 1;
  box-sizing: content-box;
  min-width: 10vw;
  min-height: calc(100vh - 120px);
  background-color: rgb(255, 255, 255);
  border-bottom: 1px solid #e5e5e5;
  border-right: 1px solid #e5e5e5;
}

main {
  flex: 9;
}

.foot {
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
  height: 58px;
  background-color: #ffffff;
}
</style>

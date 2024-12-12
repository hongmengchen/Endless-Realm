<template>
  <div class="login-container">
    <el-card class="box-card">
      <div class="login-body">
        <div class="login-title">后台管理</div>
        <el-form ref="form" :model="adminForm">
          <el-input
            placeholder="请输入管理员账号"
            v-model="adminForm.username"
            class="login-input"
          >
            <template v-slot:prepend>
              <el-icon><User /></el-icon>
            </template>
          </el-input>
          <el-input
            placeholder="请输入管理员密码"
            v-model="adminForm.password"
            class="login-input"
            @keyup.enter="login"
            show-password
          >
            <template v-slot:prepend>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
          <div class="login-submit">
            <el-button type="primary" @click="login">登录</el-button>
          </div>
          <div class="other-submit">
            <router-link to="/login" class="sign-in-text">用户登录</router-link>
          </div>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import adminAPI from "@/api/adminAPI";
import { mapActions, mapState } from "vuex";
import { Lock, User } from "@element-plus/icons-vue";

export default {
  name: "adminLoginPage",
  components: { Lock, User },
  computed: {
    ...mapState("admin", ["adminInfo"]), // 映射 admin 模块的 adminInfo 状态
  },
  data() {
    return {
      adminForm: {
        username: "admin",
        password: "password",
      },
    };
  },
  methods: {
    ...mapActions("admin", ["updateAdminInfo"]), // 映射 admin 模块的 updateAdminInfo 方法

    // 管理员登录
    async login() {
      const res = await adminAPI.login(
        this.adminForm.username,
        this.adminForm.password
      );
      if (res.data.status_code === 1) {
        console.log("管理员登录成功");
        // 更新管理员信息到 Vuex
        this.updateAdminInfo(res.data.data);
        console.log("login-管理员登录：" + this.adminInfo.username);
        // 跳转到后台管理页面
        this.$router.push("/admin-platform");
      } else {
        console.log(res.data.msg);
        this.$message.error(res.data.msg);
      }
    },
  },
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100%;
  background-color: #f1f1f1;
}
.login-body {
  padding: 30px;
  width: 400px;
  height: 100%;
}
.login-title {
  padding-bottom: 30px;
  text-align: center;
  font-weight: 600;
  font-size: 20px;
  color: #409eff;
  cursor: pointer;
}
.login-input {
  margin-bottom: 20px;
}
.login-submit {
  display: flex;
  justify-content: center;
}
.sign-in-text {
  color: #409eff;
  font-size: 16px;
  text-decoration: none;
  line-height: 28px;
}
.other-submit {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}
</style>

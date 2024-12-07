<template>
  <div class="login-container">
    <el-card class="box-card">
      <div class="login-body">
        <div class="login-title" @click="toIndex">Endless Realm · 无垠之界</div>
        <el-form ref="form" :model="userForm">
          <el-input
            placeholder="请输入账号..."
            v-model="userForm.username"
            class="login-input"
          >
            <template v-slot:prepend>
              <el-icon><User /></el-icon>
            </template>
          </el-input>
          <el-input
            placeholder="请输入密码..."
            v-model="userForm.password"
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
            <router-link to="/register" class="sign-in-text">注册</router-link>
            <router-link to="/login-admin" class="sign-in-text"
              >管理员登录</router-link
            >
          </div>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import UserAPI from "@/api/userAPI";
import { Lock, User } from "@element-plus/icons-vue";

export default {
  name: "userLoginPage",
  components: { Lock, User },
  data() {
    return {
      userForm: {
        username: "张三",
        password: "123456",
      },
    };
  },
  methods: {
    // 用户登录
    async login() {
      const res = await UserAPI.login(
        this.userForm.username,
        this.userForm.password
      );
      console.log(res);
      if (res.data.status_code === 1) {
        console.log("登录成功");
        this.$router.push("/");
      } else {
        console.log(res.data.msg);
        this.$message.error(res.data.msg);
      }
    },
    // 跳转首页
    toIndex() {
      this.$router.replace({ path: "/" });
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

.sign-in-container {
  padding: 0 10px;
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

<template>
  <div class="sign-in-container">
    <el-card class="box-card">
      <div class="sign-in-body">
        <div class="sign-in-title">注册</div>
        <el-input
          placeholder="请输入账号..."
          maxlength="30"
          v-model="userForm.username"
          class="sign-in-input"
          clearable
        >
          <template v-slot:prepend>
            <el-icon><User /></el-icon>
          </template>
        </el-input>
        <el-input
          placeholder="请输入手机号..."
          maxlength="11"
          v-model="userForm.phone"
          class="sign-in-input"
          clearable
        >
          <template v-slot:prepend>
            <el-icon><Phone /></el-icon>
          </template>
        </el-input>
        <el-input
          placeholder="请输入密码..."
          show-password
          maxlength="32"
          v-model="userForm.password"
          class="sign-in-input"
          clearable
        >
          <template v-slot:prepend>
            <el-icon><Lock /></el-icon>
          </template>
        </el-input>
        <el-input
          placeholder="请再次输入密码..."
          show-password
          maxlength="32"
          v-model="password2"
          @keyup.enter="signIn"
          class="sign-in-input"
          clearable
        >
          <template v-slot:prepend>
            <el-icon><Lock /></el-icon>
          </template>
        </el-input>
        <div class="sign-in-submit">
          <el-button type="primary" @click="signIn">提交</el-button>
        </div>
        <div class="login-container">
          <span @click="toLogin" class="login-text">登录</span>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import UserAPI from "@/api/userAPI";
import { Lock, Phone, User } from "@element-plus/icons-vue";

export default {
  name: "userRegisterPage",
  components: { Phone, Lock, User },
  data() {
    return {
      password2: "",
      userForm: {
        username: "",
        password: "",
        phone: "",
        email: "",
        avatar:
          "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
        status: 1,
      },
    };
  },
  methods: {
    toLogin() {
      this.$router.replace({ path: "/login" });
    },
    // 用户注册
    signIn() {
      if (this.userForm.username && this.userForm.password) {
        if (this.userForm.password !== this.password2) {
          this.$message.error("两次输入的密码不相同！");
        } else {
          console.log("发送的请求数据：", this.userForm);
          UserAPI.register(this.userForm).then((res) => {
            if (res.data.status_code === 1) {
              this.$message.success("注册成功！");
              this.$router.replace({ path: "/login" });
            } else {
              this.$message.error(res.data.msg);
            }
          });
        }
      } else {
        this.$message.error("注册信息未填写完整！");
      }
    },
  },
};
</script>

<style scoped>
.sign-in-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100%;
  background-color: #f1f1f1;
}

.sign-in-body {
  padding: 30px;
  width: 400px;
  height: 100%;
}

.sign-in-title {
  padding-bottom: 30px;
  text-align: center;
  font-weight: 600;
  font-size: 20px;
  color: #409eff;
}

.sign-in-input {
  margin-bottom: 20px;
}
.sign-in-submit {
  display: flex;
  justify-content: center;
}
.login-container {
  padding: 0 10px;
}
.login-text {
  color: #409eff;
  font-size: 16px;
  cursor: pointer;
}
</style>

<template>
  <el-container style="height: 100vh">
    <el-row type="flex" justify="center" align="middle" style="height: 100%">
      <el-col :span="8">
        <el-card>
          <h2 style="text-align: center">用户登录</h2>
          <el-form :model="form" label-width="100px" ref="form">
            <el-form-item label="用户名" prop="username" :rules="usernameRules">
              <el-input
                v-model="form.username"
                placeholder="请输入用户名"
              ></el-input>
            </el-form-item>

            <el-form-item label="密码" prop="password" :rules="passwordRules">
              <el-input
                v-model="form.password"
                type="password"
                placeholder="请输入密码"
              ></el-input>
            </el-form-item>

            <el-form-item>
              <el-button
                type="primary"
                @click="login"
                :loading="loading"
                :disabled="loading"
              >
                登录
              </el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </el-container>
</template>

<script>
import User from "@/api/user";

export default {
  name: "LoginPage",
  data() {
    return {
      form: {
        username: "",
        password: "",
      },
      loading: false,
      usernameRules: [
        { required: true, message: "请输入用户名", trigger: "blur" },
      ],
      passwordRules: [
        { required: true, message: "请输入密码", trigger: "blur" },
      ],
    };
  },
  methods: {
    async login() {
      const res = await User.login(this.form.username, this.form.password);
      console.log(res);
      if (res.data === "success") {
        this.$router.push("/");
      } else {
        this.$message.error("用户名或密码错误");
      }
    },
  },
};
</script>

<style scoped>
/* 样式 */
</style>

<template>
  <el-container style="height: 100vh;">
    <el-row type="flex" justify="center" align="middle" style="height: 100%;">
      <el-col :span="8">
        <el-card>
          <h2 style="text-align: center;">用户注册</h2>
          <el-form :model="form" label-width="100px" ref="form">
            <el-form-item label="用户名" prop="username" :rules="usernameRules">
              <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
            </el-form-item>

            <el-form-item label="密码" prop="password" :rules="passwordRules">
              <el-input
                v-model="form.password"
                type="password"
                placeholder="请输入密码"
              ></el-input>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="handleSubmit" :loading="loading" :disabled="loading">
                注册
              </el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </el-container>
</template>


<script>
import axios from "axios";
import { ElMessage } from "element-plus";

export default {
  data() {
    return {
      form: {
        username: "",
        password: "",
      },
      loading: false,
      usernameRules: [
        {required: true, message: "请输入用户名", trigger: "blur"},
      ],
      passwordRules: [
        {required: true, message: "请输入密码", trigger: "blur"},
      ],
    };
  },
  methods: {
    handleSubmit() {
      this.$refs.form.validate(async (valid) => {
        if (valid) {
          this.loading = true;
          try {
            const response = await axios.post("http://localhost:8080/backend_war_exploded/user/register", this.form);
            if (response.data === "success") {
              ElMessage.success("注册成功！");
              // this.$router.push("/login");  // 重定向到登录页面
            } else {
              ElMessage.error("注册失败，请重试！");
            }
          } catch (error) {
            ElMessage.error("请求失败，请稍后重试！");
          } finally {
            this.loading = false;
          }
        } else {
          console.log("表单验证失败！");
        }
      });
    },
  },
};
</script>

<style>

</style>


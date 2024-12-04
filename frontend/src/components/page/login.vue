<!-- src/components/page/login.vue -->
<template>
  <div>
    <!-- 登录表单或其他内容 -->
    <h1>Login Page</h1>
    <form @submit.prevent="handleLogin">
      <input type="text" v-model="username" placeholder="Username" />
      <input type="password" v-model="password" placeholder="Password" />
      <button type="submit">Login</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'LoginPage', // 修改组件名为多词形式
  data() {
    return {
      username: '',
      password: ''
    };
  },
  methods: {
    handleLogin() {
      // 处理登录逻辑
      console.log('Logging in with:', this.username, this.password);
    }
  }
};
</script>

<style scoped>
/* 样式 */
</style>

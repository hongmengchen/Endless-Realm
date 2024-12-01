<template>
  <div>
    <form @submit.prevent="register">
      <input v-model="username" type="text" placeholder="Username" required />
      <input v-model="password" type="password" placeholder="Password" required />
      <button type="submit">Register</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      username: '',
      password: '',
    };
  },
  methods: {
    register() {
      const user = {
        username: this.username,
        password: this.password,
      };

      // 使用 axios 向后端发送 POST 请求
      axios.post('http://localhost:8080/backend_Web_exploded/user/register', user)
        .then(response => {
          console.log(response.data);  // 确认这里是否有 data 属性
          alert(response.data);
        })
        .catch(error => {
          console.error(error.response.data);  // 检查 error response 的格式
          alert(error.response.data);
        });
    },
  },
};
</script>

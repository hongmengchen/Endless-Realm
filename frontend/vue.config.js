const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

module.exports = {
  devServer: {
    port:8081 ,  // 启动端口
    proxy: 'http://localhost:8080',  // 后端服务器地址
  },
};

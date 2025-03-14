const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,

  //  配置开发服务器
  devServer: {
    port: 5173, // Vue 运行在 http://localhost:5173
    proxy: {
      "/api": {
        target: "http://localhost:8080", // 代理到 Spring Boot
        changeOrigin: true,
        pathRewrite: { "^/api": "" },
      },
    },
  },
});

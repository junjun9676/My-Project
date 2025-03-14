import { createApp } from "vue";
import App from "./App.vue";
import router from "./router"; // 确保你有 router
import zh from "./locales/zh.json";
import en from "./locales/en.json";
import { createI18n } from "vue-i18n";

// 创建 i18n 实例
const i18n = createI18n({
  legacy: false, // 需要使用 Composition API 时设置为 false
  locale: "zh", // 默认语言
  fallbackLocale: "en", // 备用语言
  messages: {
    zh,
    en,
  },
});

createApp(App).use(i18n).use(router).mount("#app");

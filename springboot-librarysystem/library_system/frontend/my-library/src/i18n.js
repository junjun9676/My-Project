import { createI18n } from "vue-i18n";
import en from "./locales/en.json";
import zh from "./locales/zh.json";

const i18n = createI18n({
  legacy: false, // 使用 Composition API 需要设置为 false
  locale: localStorage.getItem("lang") || "zh", // 默认语言
  fallbackLocale: "en", // 备用语言
  messages: {
    en,
    zh,
  },
});

export default i18n;

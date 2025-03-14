<template>
  <div class="home">
    <h1>{{ t("welcome") }}</h1>
    <router-link to="/books">
      <button>{{ t("view_books") }}</button>
    </router-link>
  </div>
</template>

<script setup>
import { useI18n } from "vue-i18n";

const { t } = useI18n(); // 这里解构 t 变量，让模板可以使用 t()
</script>

<style scoped>
.home {
  text-align: center;
  margin-top: 50px;
}
button {
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
  background-color: #4caf50;
  color: white;
  border: none;
}
button:hover {
  background-color: #45a049;
}
</style>

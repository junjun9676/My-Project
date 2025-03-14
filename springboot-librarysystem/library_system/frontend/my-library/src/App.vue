<template>
  <div id="app">
    <nav>
      <router-link to="/">{{ $t("home") }}</router-link> |
      <router-link to="/books">{{ $t("books") }}</router-link>
      <select v-model="$i18n.locale" @change="changeLang">
        <option value="zh">中文</option>
        <option value="en">English</option>
      </select>
    </nav>
    <router-view />
  </div>
</template>

<script setup>
import { useI18n } from "vue-i18n";
import { watch } from "vue";

const { locale } = useI18n();

// 监听 locale 变化，自动存入 localStorage
watch(locale, (newLang) => {
  localStorage.setItem("lang", newLang);
});
</script>

<style>
nav {
  padding: 10px;
  background: #f4f4f4;
  text-align: center;
}
nav a {
  margin: 0 10px;
  text-decoration: none;
  color: black;
}
</style>

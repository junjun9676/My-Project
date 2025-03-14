import { createRouter, createWebHistory } from "vue-router"; //
import HomeView from "@/views/HomeView.vue";
import BookList from "@/components/BookList.vue";

const routes = [
  { path: "/", component: HomeView },
  { path: "/books", component: BookList },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

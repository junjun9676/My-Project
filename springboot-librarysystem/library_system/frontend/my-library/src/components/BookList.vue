<template>
  <div class="book-list">
    <h1>📚 {{ t("title.libraryManagement") }}</h1>
    <div class="controls">
      <div class="search-container">
        <input
          v-model="searchQuery"
          :placeholder="t('placeholder.searchBook')"
          class="search-input"
          @input="handleSearch"
        />
        <button class="search-btn" @click="handleSearch">
          🔍 {{ t("button.search") }}
        </button>
      </div>
      <button class="refresh-btn" @click="fetchPagedBooks">
        🔄 {{ t("button.refresh") }}
      </button>
    </div>

    <!-- Pagination -->
    <div class="pagination">
      <span>{{ t("pagination.perPage") }}</span>
      <select v-model="pageSize" @change="handlePageChange">
        <option value="5">5</option>
        <option value="10">10</option>
        <option value="20">20</option>
        <option value="All">All</option>
      </select>
      <button
        :disabled="currentPage <= 1"
        @click="goToPage(currentPage - 1)"
        class="page-btn"
      >
        {{ t("pagination.previous") }}
      </button>
      <span>{{ currentPage }} / {{ totalPages }}</span>
      <button
        :disabled="currentPage >= totalPages"
        @click="goToPage(currentPage + 1)"
        class="page-btn"
      >
        {{ t("pagination.next") }}
      </button>
    </div>

    <!-- Add New Book -->
    <div class="form-container">
      <h2>{{ t("title.addBook") }}</h2>
      <form @submit.prevent="addNewBook" class="add-form">
        <div class="form-group">
          <label for="bookName">{{ t("label.bookName") }}</label>
          <input
            id="bookName"
            v-model="newBook.name"
            :placeholder="t('placeholder.bookName')"
            required
          />
        </div>
        <div class="form-group">
          <label for="bookType">{{ t("label.bookType") }}</label>
          <input
            id="bookType"
            v-model="newBook.type"
            :placeholder="t('placeholder.bookType')"
            required
          />
        </div>
        <div class="form-group">
          <label for="bookDescription">{{ t("label.description") }}</label>
          <textarea
            id="bookDescription"
            v-model="newBook.description"
            :placeholder="t('placeholder.description')"
            rows="3"
          ></textarea>
        </div>
        <button type="submit" class="submit-btn">
          ➕ {{ t("button.add") }}
        </button>
      </form>
    </div>

    <!-- Book List -->
    <div class="table-container">
      <h2>{{ t("title.bookList") }}</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>{{ t("label.bookName") }}</th>
            <th>{{ t("label.bookType") }}</th>
            <th>{{ t("label.description") }}</th>
            <th>{{ t("label.actions") }}</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="book in displayedBooks" :key="book.id">
            <td>{{ book.id }}</td>
            <td>{{ book.name }}</td>
            <td>{{ book.type }}</td>
            <td>{{ book.description }}</td>
            <td>
              <div class="action-buttons">
                <button @click="editBook(book)" class="edit-btn">
                  ✏️ {{ t("button.edit") }}
                </button>
                <button @click="deleteBookById(book.id)" class="delete-btn">
                  🗑️ {{ t("button.delete") }}
                </button>
              </div>
            </td>
          </tr>
          <tr v-if="displayedBooks.length === 0">
            <td colspan="5">{{ t("message.noBooksFound") }}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Edit Dialog -->
    <dialog ref="editDialog" class="edit-dialog">
      <h2>{{ t("title.editBook") }}</h2>
      <form @submit.prevent="saveEdit" class="edit-form">
        <div class="form-group">
          <label for="editBookName">{{ t("label.bookName") }}</label>
          <input
            id="editBookName"
            v-model="editingBook.name"
            :placeholder="t('placeholder.bookName')"
            required
          />
        </div>
        <div class="form-group">
          <label for="editBookType">{{ t("label.bookType") }}</label>
          <input
            id="editBookType"
            v-model="editingBook.type"
            :placeholder="t('placeholder.bookType')"
            required
          />
        </div>
        <div class="form-group">
          <label for="editBookDescription">{{ t("label.description") }}</label>
          <textarea
            id="editBookDescription"
            v-model="editingBook.description"
            :placeholder="t('placeholder.description')"
            rows="3"
          ></textarea>
        </div>
        <div class="form-actions">
          <button type="submit" class="save-btn">
            💾 {{ t("button.save") }}
          </button>
          <button type="button" @click="closeEditDialog" class="cancel-btn">
            ❌ {{ t("button.cancel") }}
          </button>
        </div>
      </form>
    </dialog>
  </div>
</template>

<script setup>
import { useI18n } from "vue-i18n";
import { ref, onMounted, computed, watch, watchEffect } from "vue";
import { getPagedBooks, addBook, deleteBook, updateBook } from "@/api";

// 数据变量
const { t, locale } = useI18n(); // 获取语言环境
// 读取存储的语言
locale.value = localStorage.getItem("userLanguage") || "en";
const books = ref([]);
const newBook = ref({ name: "", type: "", description: "" });
const searchQuery = ref("");
const currentPage = ref(1);
const pageSize = ref(10);
const totalBooks = ref(0);
const editDialog = ref(null);
const editingBook = ref({ id: null, name: "", type: "", description: "" });

// 监听语言变化
watchEffect(() => {
  locale.value = localStorage.getItem("userLanguage") || "en";
});

// 计算总页数
const totalPages = computed(() => {
  return pageSize.value === "All"
    ? 1
    : Math.ceil(totalBooks.value / pageSize.value) || 1;
});

// 计算当前显示的书籍
const displayedBooks = computed(() => {
  return searchQuery.value.trim()
    ? books.value.filter((book) =>
        book.name.toLowerCase().includes(searchQuery.value.toLowerCase())
      )
    : books.value;
});

// 搜索功能
const handleSearch = () => {
  if (!searchQuery.value.trim()) fetchPagedBooks();
};

// 监听搜索框变化
watch(searchQuery, (newVal) => {
  if (!newVal.trim()) fetchPagedBooks();
});

// 获取分页书籍数据
const fetchPagedBooks = async () => {
  try {
    console.log(
      `Fetching books for page ${currentPage.value} with size ${pageSize.value}`
    );

    // If pageSize is 'All', set it to a very high number or handle accordingly
    const currentPageSize =
      pageSize.value === "All" ? totalBooks.value : pageSize.value;

    const response = await getPagedBooks(currentPage.value, currentPageSize);

    console.log("print the response data: ", response.data);

    if (!response?.data?.data) {
      console.error("数据格式错误:", response);
    } else {
      books.value = response.data.data.items || [];
      totalBooks.value = response.data.data.total || 0;
    }
  } catch (error) {
    console.error("获取书籍失败:", error);
  }
};

// 切换分页
const goToPage = (page) => {
  if (page < 1 || page > totalPages.value) return;
  if (pageSize.value !== "All") {
    currentPage.value = page;
    fetchPagedBooks();
  }
};

const handlePageChange = () => {
  currentPage.value = 1;
  fetchPagedBooks();
};

// 添加书籍
const addNewBook = async () => {
  try {
    await addBook(newBook.value);
    newBook.value = { name: "", type: "", description: "" };
    fetchPagedBooks();
  } catch (error) {
    console.error("添加书籍失败:", error);
    alert("添加书籍失败");
  }
};

// 删除书籍
const deleteBookById = async (id) => {
  if (confirm("确定要删除吗？")) {
    try {
      await deleteBook(id);
      fetchPagedBooks();
    } catch (error) {
      console.error("删除书籍失败:", error);
      alert("删除书籍失败");
    }
  }
};

// 编辑书籍
const editBook = (book) => {
  editingBook.value = { ...book };
  editDialog.value.showModal();
};

// 保存编辑
const saveEdit = async () => {
  try {
    console.log("更新书籍 ID:", editingBook.value.id);
    console.log("更新数据:", editingBook.value);
    await updateBook(editingBook.value.id, editingBook.value);
    console.log("更新成功！");
    closeEditDialog();
    fetchPagedBooks();
  } catch (error) {
    console.error("更新书籍失败:", error);
    alert("更新书籍失败");
  }
};

// 关闭编辑对话框
const closeEditDialog = () => {
  editDialog.value.close();
};

// 初始化数据
onMounted(() => {
  fetchPagedBooks();
});
</script>

<style scoped>
.book-list {
  font-family: "Arial", sans-serif;
  max-width: 100%;
  margin: 0 auto;
  padding: 20px;
  color: #333;
}

h1 {
  text-align: center;
  color: #2c3e50;
  margin-bottom: 30px;
}

h2 {
  color: #2c3e50;
  border-bottom: 1px solid #eee;
  padding-bottom: 10px;
  margin-top: 30px;
}

.controls {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.search-container {
  display: flex;
  gap: 10px;
}

.search-input {
  padding: 8px 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  width: 250px;
}

button {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s;
}

.search-btn {
  background-color: #2196f3;
  color: white;
}

.search-btn:hover {
  background-color: #0b7dda;
}

.refresh-btn {
  background-color: #4caf50;
  color: white;
}

.refresh-btn:hover {
  background-color: #45a049;
}

.pagination {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 20px 0;
  gap: 10px;
}

.page-btn {
  background-color: #2196f3;
  color: white;
}

.page-btn:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}

select {
  padding: 5px;
  border-radius: 4px;
  border: 1px solid #ddd;
}

.form-container {
  background-color: #f9f9f9;
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 30px;
}

.add-form {
  display: grid;
  gap: 15px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

label {
  font-weight: bold;
  color: #555;
}

input,
textarea {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-family: "Arial", sans-serif;
}

textarea {
  resize: vertical;
}

.submit-btn {
  background-color: #4caf50;
  color: white;
  padding: 10px;
  justify-self: start;
  margin-top: 10px;
}

.submit-btn:hover {
  background-color: #45a049;
}

.table-container {
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  margin-bottom: 30px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th {
  background-color: #f2f2f2;
  padding: 12px 15px;
  text-align: left;
  font-weight: bold;
}

td {
  padding: 12px 15px;
  border-bottom: 1px solid #ddd;
  text-align: left;
}

tr:last-child td {
  border-bottom: none;
}

tr:hover {
  background-color: #f5f5f5;
}

.action-buttons {
  display: flex;
  gap: 10px;
}

.edit-btn {
  background-color: #2196f3;
  color: white;
}

.edit-btn:hover {
  background-color: #0b7dda;
}

.delete-btn {
  background-color: #f44336;
  color: white;
}

.delete-btn:hover {
  background-color: #d32f2f;
}

.edit-dialog {
  border: none;
  padding: 25px;
  border-radius: 8px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
  max-width: 500px;
  width: 100%;
}

.edit-dialog::backdrop {
  background-color: rgba(0, 0, 0, 0.5);
}

.edit-form {
  display: grid;
  gap: 15px;
}

.form-actions {
  display: flex;
  gap: 10px;
  margin-top: 10px;
}

.save-btn {
  background-color: #4caf50;
  color: white;
}

.save-btn:hover {
  background-color: #45a049;
}

.cancel-btn {
  background-color: #f44336;
  color: white;
}

.cancel-btn:hover {
  background-color: #d32f2f;
}
</style>

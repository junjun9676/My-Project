import axios from "axios";

// API Base URL - adjust this to match your Spring Boot backend
const API_URL = "http://localhost:8080/";

// Create an axios instance with default config
const apiClient = axios.create({
  baseURL: API_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

// Get paginated books
export const getPagedBooks = (page, size) => {
  return apiClient.get(`/books/${page}/${size}`);
};

// Get all books
export const getAllBooks = () => {
  return apiClient.get("/books");
};

// Add a new book
export const addBook = (bookData) => {
  return apiClient.post("/books", bookData);
};

// Update a book
export const updateBook = (id, bookData) => {
  // The id is included in the bookData object, no need to include it in the URL
  return apiClient.put(`/books`, bookData);
};

// Delete a book
export const deleteBook = (id) => {
  return apiClient.delete(`/books/${id}`);
};

export default {
  getPagedBooks,
  getAllBooks,
  addBook,
  updateBook,
  deleteBook,
};

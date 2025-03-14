//package com.jun.library_system.service.impl;
//
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.jun.library_system.dao.BookDao;
//import com.jun.library_system.domain.Book;
//import com.jun.library_system.service.BookService;
//import com.jun.library_system.service.IBookService;
//import lombok.Getter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
////This is the using BookDao as the repository layer to do crud.
//@Service
//public class BookServiceImpl implements BookService{
//
//    @Autowired
//    private BookDao bookDao;
//
//    @Override
//    public boolean save(Book book) {
//        return bookDao.insert(book) > 0;
//    }
//
//    @Override
//    public boolean delete(Integer id) {
//        return bookDao.deleteById(id) > 0;
//    }
//
//    @Override
//    public boolean update(Book book) {
//        return bookDao.updateById(book) > 0;
//    }
//
//    @Override
//    public Book getById(Integer id) {
//        return bookDao.selectById(id);
//    }
//
//    @Override
//    public List<Book> getAllBook() {
//        return bookDao.selectList(null);
//    }

//    @Override
//    public IPage<Book> getPage(int currentPage, int pageSize) {
//        IPage<Book> page = new Page<>(currentPage,pageSize);
//        return bookDao.selectPage(page, null);
//    }

//    public PagedResult<Book> getPage(int currentPage, int pageSize) {
//        int offset = (currentPage - 1) * pageSize;
//        List<Book> books = bookDao.getPagedBooks(pageSize, offset);
//        long totalCount = bookDao.getTotalCount();
//
//        return new PagedResult<>(books, totalCount, currentPage, pageSize);
//    }
//
//    @Getter
//    public static class PagedResult<T> {
//        private List<T> items;
//        private long total;
//        private int currentPage;
//        private int pageSize;
//
//        public PagedResult(List<T> items, long total, int currentPage, int pageSize) {
//            this.items = items;
//            this.total = total;
//            this.currentPage = currentPage;
//            this.pageSize = pageSize;
//        }
//
//    }
//}

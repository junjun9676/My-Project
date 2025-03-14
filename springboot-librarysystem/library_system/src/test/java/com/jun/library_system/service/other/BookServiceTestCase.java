//package com.jun.library_system.service;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.jun.library_system.domain.Book;
//import com.jun.library_system.service.impl.BookServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
///*
//* Using the repository layer Dao to do CRUD
// */
//
//@SpringBootTest
//public class BookServiceTestCase {
//
//    @Autowired
//    private BookService bookService;
//
//    @Test
//    void testGetById(){
//        System.out.println(bookService.getById(2));
//    }
//
//
//    @Test
//    void testSave(){
//        Book book = new Book();
//        book.setType("English");
//        book.setName("English teacher book");
//        book.setDescription("This is a guidance book for english teacher");
//        bookService.save(book);
//    }
//
//    @Test
//    void testUpdate(){
//        Book book = new Book();
//        book.setId(19);
//        book.setType("English");
//        book.setName("English teacher book");
//        book.setDescription("This is a guidance book for english teacher");
//        System.out.println(bookService.update(book)? "update successfull" : "error update");
//    }
//
//    @Test
//    void testDelete(){
//        System.out.println( bookService.delete(1808367620)? "delete successfully" : "error delete");
//        System.out.println( bookService.delete(1808367619)? "delete successfully" : "error delete");
//        System.out.println( bookService.delete(1808367618)? "delete successfully" : "error delete");
//    }
//
//    @Test
//    void testGetAll(){
//        List<Book> books = bookService.getAllBook();
//        System.out.println(books);
//    }
//
////    @Test
////    void testGetPage(){
////        BookServiceImpl.PagedResult<Book> page = bookService.getPage(2, 5);
////
////        System.out.println("Total Records: " + page.getTotal());
////        System.out.println("Current Page: " + page.getCurrentPage());
////        System.out.println("Page Size: " + page.getItems());
////        System.out.println("Records: " + page.getPageSize());
////    }
//
//
//
//}

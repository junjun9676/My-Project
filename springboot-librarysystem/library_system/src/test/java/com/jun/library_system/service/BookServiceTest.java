package com.jun.library_system.service;

import com.jun.library_system.domain.Book;
import com.jun.library_system.service.impl.BookServiceImpl2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// Using the Iservice of MP
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private IBookService iBookService;


    @Test
    void testGetById(){
        System.out.println(iBookService.getById(2));
    }


    @Test
    void testSave(){
        Book book = new Book();
        book.setType("English");
        book.setName("English teacher book");
        book.setDescription("This is a guidance book for english teacher");
        iBookService.save(book);
    }

    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(19);
        book.setType("English");
        book.setName("English teacher book");
        book.setDescription("This is a guidance book for english teacher");
        System.out.println(iBookService.updateById(book)? "update successfull" : "error update");
    }

    @Test
    void testDelete(){
        System.out.println( iBookService.removeById(19)? "delete successfully" : "error delete");

    }

    @Test
    void testGetAll(){
        List<Book> books = iBookService.list();
        System.out.println(books);
    }

    @Test
    void testGetPage(){
        PagedResult<Book> page = iBookService.getPage(2, 5);

        System.out.println("Total Records: " + page.getTotal());
        System.out.println("Current Page: " + page.getCurrentPage());
        System.out.println("Page Size: " + page.getItems());
        System.out.println("Records: " + page.getPageSize());
    }


}

package com.jun.library_system.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jun.library_system.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.jun.library_system.dao.BookDao;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class BookDaoTestCase {

    @Autowired
    private BookDao bookDao;

   @Test
    void testGetById(){
       System.out.println(bookDao.selectById(2));
   }

   @Test
    void testSave(){
       Book book = new Book();
       book.setType("English");
       book.setName("English teacher book");
       book.setDescription("This is a guidance book for english teacher");
       bookDao.insert(book);
   }

    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(19);
        book.setType("English");
        book.setName("English teacher book");
        book.setDescription("This is a guidance book for english teacher");
        int rowAffected = bookDao.updateById(book);
        System.out.println(rowAffected > 0 ? "update successfull" : "error update");
    }

    @Test
    void testDelete(){
    int rowAffected = bookDao.deleteById(19);
        System.out.println(rowAffected > 0 ? "delete successfull" : "error delete");
    }

    @Test
    void testGetAll(){
        List<Book> books = bookDao.selectList(null);
        System.out.println(books);
    }

    @Test
    void testGetPage(){
        IPage<Book> page = new Page<>(2,5);
        bookDao.selectPage(page, null);
    }

    @Test
    void testGetBy() {
        String name = "The"; // Example name to search
        
        LambdaQueryWrapper<Book> qw = new LambdaQueryWrapper<>();

        qw.like(Book::getName, name);

        System.out.println(qw); // Should print only filtered books
    }


}

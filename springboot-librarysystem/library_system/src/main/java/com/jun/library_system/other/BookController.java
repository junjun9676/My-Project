package com.jun.library_system.other;


import com.jun.library_system.domain.Book;
import com.jun.library_system.service.IBookService;
import com.jun.library_system.service.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;


    @GetMapping
    public List<Book> getAll(){
        return bookService.list();
    }

    @PostMapping
    public Boolean save(@RequestBody Book book){
        return bookService.saveBook(book);
    }


    @PutMapping
    public Boolean update(@RequestBody Book book) {
        return bookService.modify(book);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return bookService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Book book = bookService.getById(id);

        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found with ID: " + id);
        }

        return ResponseEntity.ok(book);
    }

    @GetMapping("/{page}/{size}")
    public PagedResult<Book> getPage(@PathVariable int page, @PathVariable int size){
        return bookService.getPage(page, size);

    }


}


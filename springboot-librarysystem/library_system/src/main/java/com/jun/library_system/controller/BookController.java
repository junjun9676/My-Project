package com.jun.library_system.controller;


import com.jun.library_system.controller.utils.Result;
import com.jun.library_system.domain.Book;
import com.jun.library_system.service.IBookService;
import com.jun.library_system.service.PagedResult;
import com.jun.library_system.service.impl.BookServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")  // 允许 Vue 访问后端
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;


    @GetMapping
    public Result getAll(){
       return new Result(true, bookService.list());
    }

    @PostMapping
    public Result save(@RequestBody Book book){
        return new Result(bookService.saveBook(book));    // flag = bookService.saveBook(book) , data = null (no need set also null)
    }


    @PutMapping
    public Result update(@RequestBody Book book) {
        if (book == null) {
            return new Result(false, "请求体解析失败！");
        }
        return new Result(bookService.modify(book));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return new Result(bookService.delete(id));
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        return new Result(true, bookService.getById(id));   // if fail to get will flag: true, data: null
    }

    @GetMapping("/{page}/{size}")
    public Result getPage(@PathVariable int page, @PathVariable int size){
        return new Result(true, bookService.getPage(page, size));

    }

    @GetMapping("/search")
    public Result searchBooks(@RequestParam(required = false) String query) {
        if (query == null || query.isEmpty()) {
            return new Result(false, "Query parameter is required");
        }
        return new Result(true, bookService.searchByName(query));
    }

}


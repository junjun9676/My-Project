package com.jun.library_system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.library_system.domain.Book;
import com.jun.library_system.service.impl.BookServiceImpl2;

import java.util.List;


public interface IBookService extends IService<Book> {

    boolean saveBook(Book book);
    boolean modify(Book book);
    boolean delete(Integer id);
    PagedResult<Book> getPage(int currentPage, int pageSize);
    List<Book> searchByName(String name);

}

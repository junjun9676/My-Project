package com.jun.library_system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.library_system.dao.BookDao;
import com.jun.library_system.domain.Book;
import com.jun.library_system.service.IBookService;
import com.jun.library_system.service.PagedResult;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

// This is the using MyBatis Plus ServiceImpl and the BaseMapper at the Service layer to do crud.
@Service
public class BookServiceImpl2 extends ServiceImpl<BookDao,Book> implements IBookService {

    @Autowired
    private BookDao bookDao;


    @Override
    public boolean saveBook(Book book) {
        return this.save(book) ;
    }

    @Override
    public boolean modify(Book book) {
        System.out.println("更新书籍：" + book);
        return this.updateById(book);
    }

    @Override
    public boolean delete(Integer id) {
        return this.removeById(id);
    }


    @Override
    public PagedResult<Book> getPage(int currentPage, int pageSize) {
        int offset = (currentPage - 1) * pageSize;
        List<Book> books = bookDao.getPagedBooks(pageSize, offset);
        long totalCount = bookDao.getTotalCount();

        return new PagedResult<>(books, totalCount, currentPage, pageSize);
    }

    @Override
    public List<Book> searchByName(String name) {
        List<Book> books = list();

        // Log the original query and the processed query
        System.out.println("Original query: '" + name + "'");
        String trimmedQuery = name.trim().toLowerCase(Locale.ROOT);
        System.out.println("Trimmed query: '" + trimmedQuery + "'");

        List<Book> collect = books.stream()
                .filter(book -> book.getName() != null
                        && book.getName().toLowerCase(Locale.ROOT).trim().contains(trimmedQuery))
                .collect(Collectors.toList());
        // Log the size of the list
        System.out.println("Filtered list size: " + collect.size());

        // If the list is empty, print that as well
        if (collect.isEmpty()) {
            System.out.println("No books found matching the query: " + name);
        }

        // Print the filtered list
        collect.forEach(System.out::println);
        return collect;

    }


}

package com.jun.library_system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jun.library_system.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookDao extends BaseMapper<Book> {

    @Select("SELECT id, type, name, description FROM libraryitems LIMIT #{pageSize} OFFSET #{offset}")
    List<Book> getPagedBooks(@Param("pageSize") int pageSize, @Param("offset") int offset);

    @Select("SELECT COUNT(*) FROM libraryitems")
    long getTotalCount();
}

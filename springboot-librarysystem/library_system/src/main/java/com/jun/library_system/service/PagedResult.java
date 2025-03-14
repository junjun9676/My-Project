package com.jun.library_system.service;

import lombok.Getter;

import java.lang.reflect.Type;
import java.util.List;

@Getter
public class PagedResult<T> {

    private List<T> items;
    private long total;
    private int currentPage;
    private int pageSize;

    public PagedResult(List<T> items, long total, int currentPage, int pageSize) {
        this.items = items;
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
}

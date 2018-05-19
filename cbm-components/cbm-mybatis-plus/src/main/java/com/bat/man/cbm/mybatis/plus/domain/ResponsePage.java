package com.bat.man.cbm.mybatis.plus.domain;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

public class ResponsePage<T> {

    /**
     * page: 当前页
     */
    private int page = 1;

    /**
     * size: 每页容量
     */
    private int size;

    /**
     * content: 当前页数据列表
     */
    private List<T> content;

    /**
     * total: 总数据个数
     */
    private long total;

    /**
     * totalPages: 总页数
     */
    private int totalPages;

    public ResponsePage(Page<T> page) {
        setContent(page.getRecords());
        setSize(page.getSize());
        setPage(page.getCurrent());
        setTotal(page.getTotal());
        long pages = page.getPages();
        int totalPages;
        if (pages < Integer.MAX_VALUE) {
            totalPages = (int) pages;
        } else {
            totalPages = Integer.MAX_VALUE;
        }
        setTotalPages(totalPages);
    }

    public ResponsePage() {

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}


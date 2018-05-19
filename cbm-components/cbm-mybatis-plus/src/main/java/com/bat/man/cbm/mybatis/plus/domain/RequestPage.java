package com.bat.man.cbm.mybatis.plus.domain;

public class RequestPage {

    public static final int DEFAULT_SIZE = 10;

    public static final int MAX_SIZE = 1000;

    /**
     * page: 当前页
     */
    private int page = 1;

    /**
     * size: 每页容量
     */
    private int size;

    public RequestPage() {

    }

    public int getPage() {
        return page < 1 ? 1 : page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size < 1 ? DEFAULT_SIZE : (size > MAX_SIZE ? MAX_SIZE : size);
    }

    public void setSize(int size) {
        this.size = size;
    }

}


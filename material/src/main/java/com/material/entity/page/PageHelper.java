package com.material.entity.page;

public class PageHelper{
    private int page;
    private int rows;
    private String sort;
    private String order;

    public int getPage()
    {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return this.rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getMin() {
        return (this.page - 1) * this.rows;
    }

    public int getMax() {
        return this.page * this.rows;
    }
}
package com.pm.restaurantservice.dto;

public class Pagination {
    private Long total;
    private Integer page;
    private Long pages;

    public Pagination(Long total, Integer page, Long pages){
        this.total=total;
        this.page=page;
        this.pages=pages;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }
}

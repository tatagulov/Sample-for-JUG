package io.tatagulov.badproject.web.dto;

public class BaseListRequest {
    private Integer page = 1;
    private Integer pageSize = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return (page - 1) * pageSize;
    }

    public int getLimit() {
        return pageSize;
    }
}

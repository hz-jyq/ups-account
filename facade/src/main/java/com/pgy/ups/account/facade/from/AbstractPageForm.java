package com.pgy.ups.account.facade.from;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;

public class AbstractPageForm<T extends AbstractPageForm<T>>{


    private int pageSize;

    private  int PageNumber;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(int pageNumber) {
        PageNumber = pageNumber;
    }

    public final T enablePaging() {
        PageHelper.startPage(1, 4);
        return (T) this;
    }
}

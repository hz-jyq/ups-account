package com.pgy.ups.account.facade.from;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.pgy.ups.account.facade.model.Model;


public class AbstractPageForm<T extends AbstractPageForm<T>> extends Model {


    /**
	 * 
	 */
	private static final long serialVersionUID = -3602263301503589062L;

	private int pageSize = 1;

    private  int pageNumber = 10;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public final T enablePaging() {
        PageHelper.startPage(pageSize, pageNumber);
        return (T) this;
    }
}

package com.pgy.ups.account.facade.from;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class PageInfo<T> implements Serializable {



    private  Long  zero = new Long("0");
	
	private Long pageSize;
	
	private long currentPage;
	
	private Long total;
	
	private Long currentSize;
	
	private Long maxPage;
	
	private List<T> list;
	
	private String html;
	
	public  PageInfo(List<T> recordList) {
	    if(recordList == null){
            return;
        }
		list = new ArrayList<T>();
        list.addAll(recordList);
        Pagination pagination = PageHelper.getPagination();
        total = pagination.getTotal();
        this.pageSize = new Long(pagination.getSize());
		this.maxPage = total == zero ? zero:total/pageSize +1 ;
		this.currentSize= Long.valueOf(pagination.getCurrent());
        PageHelper.remove();
	}



	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public Long getTotal() {
		return total;
	}

	public Long getCurrentSize() {
		return currentSize;
	}

	public Long getMaxPage() {
		return maxPage;
	}

	public List<T> getList() {
		return list;
	}
	

}

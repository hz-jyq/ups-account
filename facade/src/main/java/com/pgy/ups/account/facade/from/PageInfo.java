package com.pgy.ups.account.facade.from;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;

public class PageInfo<T> {
	
	private Long pageSize;
	
	private long currentPage;
	
	private Long total;
	
	private Long currentSize;
	
	private Long maxPage;
	
	private List<T> list;
	
	private String html;
	
	public PageInfo(List<T> list,Long currentPage,Long pageSize) {
		list=new ArrayList<>();
		list.addAll(list);
		total=PageHelper.getTotal();		
		this.pageSize=pageSize;
		this.maxPage=total/pageSize +1;
		this.currentSize=(long) list.size();		
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

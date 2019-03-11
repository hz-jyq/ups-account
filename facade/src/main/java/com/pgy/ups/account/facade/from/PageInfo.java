package com.pgy.ups.account.facade.from;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.pgy.ups.account.facade.model.Model;



public class PageInfo<T> extends Model {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Long pageSize;
		
	private Long total=0L;
	
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


	public Long getTotal() {
		return total;
	}


	public List<T> getList() {
		return list;
	}
	

}

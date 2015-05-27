package com.sdkj.pmodel.ui;

import java.util.ArrayList;
import java.util.List;

public class DataGird {
	private Long total=0L;
	@SuppressWarnings("rawtypes")
	private List rows=new ArrayList();
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}
	public void setRows(@SuppressWarnings("rawtypes") List rows) {
		this.rows = rows;
	}
	
	
	public DataGird() {
	}
	@SuppressWarnings("rawtypes")
	public DataGird(Long total, List rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
}

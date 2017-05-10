package com.hao.onlineExam.model;

import java.util.List;

public class PagerModel<T> {

	private int offset;
	private int pageSize;
	private long totalSize;
	private List<T> dates;
	
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	public List<T> getDates() {
		return dates;
	}
	public void setDates(List<T> dates) {
		this.dates = dates;
	}
	
}

package com.mfs.programmer.page;

import org.springframework.stereotype.Component;

/**
 * 	分页类封装
 * @author mfs
 *
 */
@Component
public class Page {
	private int page;//表示当前页面
	private int offset;
	private int rows;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getOffset() {
		this.offset = (page -1)*rows;
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = (page -1)*rows;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

	
	
}

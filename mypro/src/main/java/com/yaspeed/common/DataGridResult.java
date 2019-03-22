package com.yaspeed.common;

import java.io.Serializable;
import java.util.List;

/**
 * bootstrap 列表封装
 * 
 * @author Administrator
 *
 */
public class DataGridResult implements Serializable {
	private static final long serialVersionUID = 6019556657702487398L;
	private long total;
	private List rows;

	public DataGridResult() {
		super();
	}

	public DataGridResult(long total, List rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
}

package com.yiran.payorder.domain;

import java.io.Serializable;
import java.util.List;

public class OrderQueryResult implements Serializable {

	private static final long serialVersionUID = -5202145773674974112L;

	private List<OrderVO> list;
	
	private int totalItems;

	public List<OrderVO> getList() {
		return list;
	}

	public void setList(List<OrderVO> list) {
		this.list = list;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	
	
}

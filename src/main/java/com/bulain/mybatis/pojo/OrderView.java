package com.bulain.mybatis.pojo;

import com.bulain.mybatis.model.Order;

public class OrderView extends Order{
	private static final long serialVersionUID = -641495605891279623L;

	private String createdAtName;
	private String updatedAtName;
	
	public OrderView(Order order){
		setId(order.getId());
		setName(order.getName());
		setNote(order.getNote());
		setWfId(order.getWfId());
		setWfStatus(order.getWfStatus());
		setCreatedBy(order.getCreatedBy());
    	setCreatedAt(order.getCreatedAt());
    	setUpdatedBy(order.getUpdatedBy());
    	setUpdatedAt(order.getUpdatedAt());
	}
	
	
	public String getCreatedAtName() {
		return createdAtName;
	}
	public void setCreatedAtName(String createdAtName) {
		this.createdAtName = createdAtName;
	}
	public String getUpdatedAtName() {
		return updatedAtName;
	}
	public void setUpdatedAtName(String updatedAtName) {
		this.updatedAtName = updatedAtName;
	}
}

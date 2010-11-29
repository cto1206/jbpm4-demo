package com.bulain.mybatis.service;

import com.bulain.common.dao.Mapper;
import com.bulain.common.service.ServiceImpl;
import com.bulain.mybatis.dao.OrderMapper;
import com.bulain.mybatis.model.Order;
import com.bulain.mybatis.pojo.OrderSearch;

public class OrderServiceImpl extends ServiceImpl<OrderSearch, Order> implements OrderService{
	private OrderMapper orderMapper;
	
	public Order getByWfId(String wfId) {
		return orderMapper.getByWfId(wfId);
	}
	
	@Override
	public Mapper<OrderSearch, Order> getMapper() {
		return orderMapper;
	}

	public OrderMapper getOrderMapper() {
		return orderMapper;
	}
	public void setOrderMapper(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}

}

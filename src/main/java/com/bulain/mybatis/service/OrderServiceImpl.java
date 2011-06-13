package com.bulain.mybatis.service;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.mybatis.dao.OrderMapper;
import com.bulain.mybatis.model.Order;
import com.bulain.mybatis.pojo.OrderSearch;

public class OrderServiceImpl extends PagedServiceImpl<OrderSearch, Order> implements OrderService {
    private OrderMapper orderMapper;

    public Order getByWfId(String wfId) {
        return orderMapper.getByWfId(wfId);
    }

    @Override
    public PagedMapper<OrderSearch, Order> getPagedMapper() {
        return orderMapper;
    }

    public OrderMapper getOrderMapper() {
        return orderMapper;
    }
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

}

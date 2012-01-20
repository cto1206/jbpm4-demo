package com.bulain.mybatis.service;

import com.bulain.common.service.PagedService;
import com.bulain.mybatis.model.Order;
import com.bulain.mybatis.pojo.OrderSearch;

public interface OrderService extends PagedService<Order, OrderSearch> {
    Order getByWfId(String wfId);
}

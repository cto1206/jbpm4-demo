package com.bulain.mybatis.service;

import com.bulain.common.service.PagedService;
import com.bulain.mybatis.model.Order;
import com.bulain.mybatis.pojo.OrderSearch;

public interface OrderService extends PagedService<OrderSearch, Order>{
	Order getByWfId(String wfId);
}

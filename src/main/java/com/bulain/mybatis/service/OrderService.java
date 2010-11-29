package com.bulain.mybatis.service;

import com.bulain.common.service.Service;
import com.bulain.mybatis.model.Order;
import com.bulain.mybatis.pojo.OrderSearch;

public interface OrderService extends Service<OrderSearch, Order>{
	Order getByWfId(String wfId);
}

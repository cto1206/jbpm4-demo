package com.bulain.mybatis.dao;

import com.bulain.common.dao.PagedMapper;
import com.bulain.mybatis.model.Order;
import com.bulain.mybatis.pojo.OrderSearch;

public interface OrderMapper extends PagedMapper<OrderSearch,Order>{
	Order getByWfId(String wfId);
}
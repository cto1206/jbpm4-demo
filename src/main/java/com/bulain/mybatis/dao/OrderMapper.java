package com.bulain.mybatis.dao;

import com.bulain.common.dao.Mapper;
import com.bulain.mybatis.model.Order;
import com.bulain.mybatis.pojo.OrderSearch;

public interface OrderMapper extends Mapper<OrderSearch,Order>{
	Order getByWfId(String wfId);
}
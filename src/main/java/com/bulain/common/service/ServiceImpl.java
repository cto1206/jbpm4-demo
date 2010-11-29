package com.bulain.common.service;

import java.util.List;

import com.bulain.common.dao.Mapper;
import com.bulain.common.page.Page;
import com.bulain.common.page.Search;


public abstract class ServiceImpl<S extends Search,T> implements Service<S,T>{
	public abstract Mapper<S,T> getMapper();
	
	public T get(Integer id){
		return getMapper().selectByPrimaryKey(id);
	}
	public void insert(T user){
		int count = getMapper().insert(user);
		if(count!=1)throw new ServiceException();
	}
	public void update(T user, boolean forced){
		int count = 0;
		if(forced){
			count = getMapper().updateByPrimaryKey(user);
		}else{
			count = getMapper().updateByPrimaryKeySelective(user);
		}
		if(count!=1)throw new ServiceException();
	}
	public void delete(Integer id){
		int count = getMapper().deleteByPrimaryKey(id);
		if(count!=1) throw new ServiceException();
	}
	
	public List<T> find(S search){
		return getMapper().find(search);
	}
	public Long count(S search){
		return getMapper().count(search);
	}
	public List<T> page(S search, Page page){
		Long count = getMapper().count(search);
		page.setCount(count.longValue());
		search.setLow(page.getLow());
		search.setHigh(page.getHigh());
		return getMapper().page(search);
	}
}

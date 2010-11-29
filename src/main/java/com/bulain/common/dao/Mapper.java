package com.bulain.common.dao;

import java.util.List;

public interface Mapper<S, T> {
	int deleteByPrimaryKey(Integer id);
    int insert(T record);
    int insertSelective(T record);
    T selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(T record);
    int updateByPrimaryKey(T record);
	
    //custom
    List<T> find(S search);
    Long count(S search);
    List<T> page(S search);
}

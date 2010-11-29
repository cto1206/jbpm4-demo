package com.bulain.common.service;

import java.util.List;

import com.bulain.common.page.Page;

public interface Service<S,T> {
	T get(Integer id);
	void insert(T user);
	void update(T user, boolean forced);
	void delete(Integer id);
	
	List<T> find(S search);
    Long count(S search);
    List<T> page(S search, Page page);
}

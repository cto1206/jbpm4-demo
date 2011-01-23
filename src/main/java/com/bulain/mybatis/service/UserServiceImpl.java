package com.bulain.mybatis.service;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.mybatis.dao.UserMapper;
import com.bulain.mybatis.model.User;
import com.bulain.mybatis.pojo.UserSearch;

public class UserServiceImpl extends PagedServiceImpl<UserSearch, User> implements UserService{
	private UserMapper userMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	@Override
	public PagedMapper<UserSearch, User> getPagedMapper() {
		return userMapper;
	}
}

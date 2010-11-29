package com.bulain.mybatis.service;

import com.bulain.common.dao.Mapper;
import com.bulain.common.service.ServiceImpl;
import com.bulain.mybatis.dao.UserMapper;
import com.bulain.mybatis.model.User;
import com.bulain.mybatis.pojo.UserSearch;

public class UserServiceImpl extends ServiceImpl<UserSearch, User> implements UserService{
	private UserMapper userMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	@Override
	public Mapper<UserSearch, User> getMapper() {
		return userMapper;
	}
}

package com.afw.springboot.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.afw.springboot.dao.UserMapper;
import com.afw.springboot.domain.entity.User;
import com.afw.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserMapper userMapper;
	
	public User findUserById(int id) {
		return userMapper.selectByPrimaryKey(id);
	}

}

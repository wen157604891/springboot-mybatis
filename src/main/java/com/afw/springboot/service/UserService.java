package com.afw.springboot.service;

import com.afw.springboot.domain.entity.User;

public interface UserService {

	public User findUserById(int id);
}

package com.ezen.farmocean.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.farmocean.cs.mapper.UserMapper;
import com.ezen.farmocean.dto.User;

@Service
public class UserServiceImpl implements UserService{

	UserMapper mapper;
	
@Override
public List<User> getList() {
	
	return mapper.getList();
}


	
}

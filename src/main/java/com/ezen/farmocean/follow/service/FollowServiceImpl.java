package com.ezen.farmocean.follow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.follow.dto.Follow;
import com.ezen.farmocean.follow.mapper.FollowMapper;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowMapper mapper;
	
	@Override
	public List<Follow> getFolloweeList(String follower_id) {
		// TODO Auto-generated method stub
		return mapper.getFolloweeList(follower_id);
	}

	@Override
	public List<Follow> getFollowerList(String followee_id) {
		// TODO Auto-generated method stub
		return mapper.getFollowerList(followee_id);
	}

}

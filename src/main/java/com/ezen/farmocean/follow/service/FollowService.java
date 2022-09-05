package com.ezen.farmocean.follow.service;

import java.util.List;

import com.ezen.farmocean.follow.dto.Follow;
import com.ezen.farmocean.member.dto.Member;

public interface FollowService {

	public List <Follow> getFolloweeList(String follower_id);
	
	public List <Follow> getFollowerList(String followee_id);

	
}

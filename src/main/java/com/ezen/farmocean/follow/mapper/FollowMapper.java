package com.ezen.farmocean.follow.mapper;

import java.util.List;

import com.ezen.farmocean.follow.dto.Follow;

public interface FollowMapper {

	List <Follow> getFolloweeList(String follower_id);
	
	List <Follow> getFollowerList(String followee_id);

}

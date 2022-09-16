package com.ezen.farmocean.prod.service;

import java.util.List;

import com.ezen.farmocean.follow.dto.Follow;

public interface EtcService {
	
	public String getMemberImageById(String member_id);
	public List<Follow> getFollow(String follower_id, String followee_id);
}

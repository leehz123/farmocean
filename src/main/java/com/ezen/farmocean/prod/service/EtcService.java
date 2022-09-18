package com.ezen.farmocean.prod.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.admin.dto.BuyInfo;
import com.ezen.farmocean.follow.dto.Follow;

public interface EtcService {
	
	public String getMemberImageById(String member_id);

	public List<Follow> getFollow(String follower_id, String followee_id);
	
	public List<BuyInfo> buyerAuthentication(String buy_id, Integer prod_idx);

	public Integer changeBuyState6(Integer buy_idx);
}

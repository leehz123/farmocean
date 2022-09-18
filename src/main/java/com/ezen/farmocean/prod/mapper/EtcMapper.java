package com.ezen.farmocean.prod.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.admin.dto.BuyInfo;
import com.ezen.farmocean.follow.dto.Follow;

public interface EtcMapper {

	public String getMemberImageById(@Param("member_id") String member_id);

	public String getMemberIdByNickname(@Param("member_nickname") String member_nickname);
	
	public List<Follow> getFollow(@Param("follower_id") String follower_id, @Param("followee_id") String followee_id);

	public List<BuyInfo> buyerAuthentication(@Param("buy_id") String buy_id, @Param("prod_idx") Integer prod_idx);
	
	public Integer changeBuyState6(@Param("buy_idx") Integer buy_idx);
}

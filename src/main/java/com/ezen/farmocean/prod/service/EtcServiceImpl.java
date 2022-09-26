package com.ezen.farmocean.prod.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.admin.dto.BuyInfo;
import com.ezen.farmocean.follow.dto.Follow;
import com.ezen.farmocean.prod.mapper.EtcMapper;

@Service
public class EtcServiceImpl implements EtcService {

	@Autowired
	EtcMapper etc;
	
	@Override
	public String getMemberImageById(String member_id) {
		return etc.getMemberImageById(member_id);
	}

	@Override
	public List<Follow> getFollow(String follower_id, String followee_id) {
		return etc.getFollow(follower_id, followee_id);
	}

	@Override
	public List<BuyInfo> buyerAuthentication(String buy_id, Integer prod_idx) {
		return etc.buyerAuthentication(buy_id, prod_idx);
	}

	@Override
	public Integer changeBuyState6(Integer buy_idx) {
		return etc.changeBuyState6(buy_idx);
	}

	@Override
	public Integer encMembers(String member_pw, String member_account, String member_name, String member_address,
			String email, String member_phoneNum, String member_id) {
		return etc.encMembers(member_pw, member_account, member_name, member_address, email, member_phoneNum, member_id);
	}

	@Override
	public Integer decPw(String member_id) {
		return etc.decPw(member_id);
	}
	
	
}

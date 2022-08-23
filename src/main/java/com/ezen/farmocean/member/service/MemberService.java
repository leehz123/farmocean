package com.ezen.farmocean.member.service;

import java.util.List;

import com.ezen.farmocean.member.dto.BuyMember;

public interface MemberService {
	
	public List <BuyMember> getList();
	
	public Integer insert(BuyMember buyer);
}


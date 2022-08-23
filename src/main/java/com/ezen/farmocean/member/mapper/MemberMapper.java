package com.ezen.farmocean.member.mapper;

import java.util.List;

import com.ezen.farmocean.member.dto.BuyMember;

public interface MemberMapper {

	List <BuyMember> getList();

	Integer insert(BuyMember buyer);
	
}

package com.ezen.farmocean.member.mapper;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ezen.farmocean.member.dto.BuyMember;
import com.ezen.farmocean.member.dto.SellMember;

public interface MemberMapper {

	List <BuyMember> getList();

	Integer insert(BuyMember buyer);
	
	Integer insertSeller(SellMember seller);
	
	String setSession(HttpSession session, String buy_id);
	
	BuyMember getMember(String buy_id);
	
}

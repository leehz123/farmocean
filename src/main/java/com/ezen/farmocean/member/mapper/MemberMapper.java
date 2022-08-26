package com.ezen.farmocean.member.mapper;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.dto.SellMember;

public interface MemberMapper {

	List <Member> getList();
	
	List <SellMember> getSellerList();

	Integer insert(Member member);
	
	Integer insertSeller(SellMember seller);
	
	String setSession(HttpSession session, String buy_id);
	
	Member getMember(String buy_id);

	SellMember getSeller(String sell_id);
	
	LoginMember loginCheck(LoginMember member);
	
	SellMember sellerLoginCheck(SellMember seller);
	 
	
}

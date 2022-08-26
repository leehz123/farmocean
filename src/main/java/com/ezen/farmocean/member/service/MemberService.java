package com.ezen.farmocean.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ezen.farmocean.member.dto.SellMember;
import com.ezen.farmocean.member.dto.Member;

public interface MemberService {
	
	public List <Member> getList();
	
	public List <SellMember> getSellerList();
	
	public Integer insert(Member member);
	
	public Integer insertSeller(SellMember seller);
	
	public String setSession(HttpSession session, String buy_id);
	
	public Member getMember(String member_id);
	
	public SellMember getSeller(String sell_id);
	
	 public Member loginCheck(Member member);
	 
	 public SellMember sellerLoginCheck(SellMember seller);
	 
	 public void logout(HttpSession session);
}


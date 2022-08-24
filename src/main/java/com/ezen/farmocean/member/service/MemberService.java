package com.ezen.farmocean.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ezen.farmocean.member.dto.BuyMember;
import com.ezen.farmocean.member.dto.SellMember;

public interface MemberService {
	
	public List <BuyMember> getList();
	
	public List <SellMember> getSellerList();
	
	public Integer insert(BuyMember buyer);
	
	public Integer insertSeller(SellMember seller);
	
	public String setSession(HttpSession session, String buy_id);
	
	public BuyMember getMember(String buy_id);
	
	public SellMember getSeller(String sell_id);
}


package com.ezen.farmocean.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.member.dto.BuyMember;
import com.ezen.farmocean.member.dto.SellMember;
import com.ezen.farmocean.member.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	
@Autowired
MemberMapper mapper;
	
@Override
public List<BuyMember> getList() {
	
	return mapper.getList();
}

@Override
public Integer insert(BuyMember user) {
	
	return mapper.insert(user);
}

@Override
public Integer insertSeller(SellMember seller) {
	
	return mapper.insertSeller(seller);
}


@Override
public String setSession(HttpSession session, String buy_id) {
	
	session.setAttribute("logined", buy_id);
	
	return "";
}

@Override
public BuyMember getMember(String buy_id) {

	return mapper.getMember(buy_id);
}


@Override
public SellMember getSeller(String sell_id) {

	return mapper.getSeller(sell_id);
}


@Override
public List<SellMember> getSellerList() {
	
	return mapper.getSellerList();
}




	
}

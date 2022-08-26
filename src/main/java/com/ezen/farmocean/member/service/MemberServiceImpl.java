package com.ezen.farmocean.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.dto.SellMember;
import com.ezen.farmocean.member.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	
@Autowired
MemberMapper mapper;
	
@Override
public List<Member> getList() {
	
	return mapper.getList();
}

@Override
public Integer insert(Member member) {
	
	return mapper.insert(member);
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
public Member getMember(String member_id) {

	return mapper.getMember(member_id);
}


@Override
public Member nickNameCheck(String member_nickName) {

	return mapper.nickNameCheck(member_nickName);
}


@Override
public List<SellMember> getSellerList() {
	
	return mapper.getSellerList();
}


@Override
public LoginMember loginCheck(LoginMember member) {

 
 return mapper.loginCheck(member); 
}

@Override
public SellMember sellerLoginCheck(SellMember seller) {
	
	return mapper.sellerLoginCheck(seller);
}

@Override
public void logout(HttpSession session) {
 session.invalidate(); // ���� �ʱ�ȭ
 }




	
}

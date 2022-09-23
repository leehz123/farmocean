package com.ezen.farmocean.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.dto.SellMember;

public interface MemberService {
	
	public List <Member> getList();
	
	public Integer insert(Member member);
	
	public String setSession(HttpSession session, String buy_id);
	
	public Member getMember(String member_id);
	
	public Member nickNameCheck(String member_nickName);
	
	public LoginMember loginCheck(LoginMember member);
	 
	public void logout(HttpSession session);

	public Member idSearch(Member member);

	public Member pwSearch(Member member);

	public Member naverLoginCheck(Member member);
	
//	public void pwChange(Member member);
	
	public Integer memberPwChange(LoginMember member);

	public void pwSearchChange(Member member);
	 
}


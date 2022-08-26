package com.ezen.farmocean.mypage.service;

import java.util.List;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.dto.SellMember;
import com.ezen.farmocean.mypage.dto.MessageBox;

public interface MessageService {
	
	// 쪽지 전체
	public List<MessageBox> getList();

	// 쪽지 내가 받은 것 만
	public List<MessageBox> getMyList(String id);
	
	// 쪽지 내가 보낸 것 만
	public List<MessageBox> getMySendList(String id);
	
	// 회원 정보 불러오기
	public List<SellMember> getSell(String id);
	
	public List<Member> getBuy(String id);
	
	// 회원 정보 수정하기

}

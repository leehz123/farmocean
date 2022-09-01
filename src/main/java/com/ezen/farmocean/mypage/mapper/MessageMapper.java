package com.ezen.farmocean.mypage.mapper;

import java.util.List;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.mypage.dto.MessageBox;

public interface MessageMapper {
	
	// 쪽지 전체
	public List<MessageBox> getList();
	
	// 쪽지 내가 받은 것 만
	public List<MessageBox> getMyList(String id);
	
	// 쪽지 내가 보낸 것 만
	public List<MessageBox> getMySendList(String id);
	
	// 회원 정보 불러오기
	public List<Member> getMemberList(String id);
	
	// 회원 정보 수정하기 (S)
	public int getUpdateinfo(Member member);
	
	// 회원 정보 수정하기 (B)
	public int getUpdateinfoB(Member member);
	
	// 회원 정보 모두 불러오기
	public List<Member> getAllMember();

	// 회원 프로필 이미지 update
	public int getUpdateImg(String image);
}

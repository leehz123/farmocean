package com.ezen.farmocean.mypage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.mypage.dto.MessageBox;

public interface MessageMapper {
	
	// 쪽지 전체
	public List<MessageBox> getList();
	
	// 쪽지 내가 받은 것 만
	public List<MessageBox> getMyList(String id);
	
	// 쪽지 내가 보낸 것 만
	public List<MessageBox> getMySendList(String id);
	
	// 쪽지 보내기
	public Integer getSendMessage(@Param("myId") String myId, @Param("id") String id, @Param("title") String title, @Param("content") String content);
	
	// 쪽지 내용 확인
	public List<MessageBox> getReadMyMessage(String id);
	
	// 쪽지 읽음 표시로 바꾸기
	public int getUpdateReadMyMessage(String id);
	
	// 회원 정보 불러오기
	public List<Member> getMemberList(String id);
	
	// 회원 정보 수정하기 (S)
	public int getUpdateinfo(Member member);
	
	// 회원 정보 수정하기 (B)
	public int getUpdateinfoB(Member member);
	
	// 회원 정보 모두 불러오기
	public List<Member> getAllMember();

	// 회원 프로필 이미지 update
	public int getUpdateImg(@Param("image") String image, @Param("id") String id);
}

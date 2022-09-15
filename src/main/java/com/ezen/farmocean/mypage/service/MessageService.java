package com.ezen.farmocean.mypage.service;

import java.util.List;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.mypage.dto.MessageBox;

public interface MessageService {
	
	// 쪽지 전체
	public List<MessageBox> getList();

	// 쪽지 내가 받은 것 만
	public List<MessageBox> getMyList(String id);
	
	// 쪽지 내가 보낸 것 만
	public List<MessageBox> getMySendList(String id);
	
	// 쪽지 보내기 (messageBox)
	public Integer getSendMessage(String myId, String id, String title, String content);
	
	// 쪽지 보내기 (SendMessageBox)
	public Integer getSendMessage2(String myId2, String id2, String title2, String content2);
	
	// 쪽지 내용 확인
	public List<MessageBox> getReadMyMessage(String id);
	
	// 쪽지 읽음 표시로 바꾸기 (messageBox)
	public int getUpdateReadMyMessage(String id);
	
	// 쪽지 읽음 표시로 바꾸기 (SendMessageBox)
	public int getUpdateReadMyMessage2(String id);
	
	// 회원 정보 불러오기	
	public List<Member> getMember(String id);
	
	// 닉네임으로 회원 정보 불러오기
	public List<Member> getNickNameMember(String nickname);
	
	// 회원 정보 수정하기 (S)
	public Integer getUpdateinfo(Member member);
	
	// 회원 정보 수정하기 (B)
	public Integer getUpdateinfoB(Member member);
	
	// 회원 정보 모두 불러오기
	public List<Member> getAllMember();
	
	// 회원 프로필 이미지 update
	public Integer getUpdateImg(String image, String id);

}

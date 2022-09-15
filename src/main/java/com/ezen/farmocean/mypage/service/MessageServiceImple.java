package com.ezen.farmocean.mypage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.mypage.dto.MessageBox;
import com.ezen.farmocean.mypage.mapper.MessageMapper;

@Service
public class MessageServiceImple implements MessageService {

	MessageMapper mapper;
	
	public MessageServiceImple(MessageMapper mapper) {
		this.mapper = mapper;
	}
	
	// 쪽지 전체
	@Override
	public List<MessageBox> getList() {
		return mapper.getList();
	}

	// 쪽지 내가 받은 것 만
	@Override
	public List<MessageBox> getMyList(String id) {
		return mapper.getMyList(id);
	}
	
	// 쪽지 내가 보낸 것 만
	@Override
	public List<MessageBox> getMySendList(String id) {
		return mapper.getMySendList(id);
	}

	// 쪽지 보내기 (MessageBox)
	@Override
	public Integer getSendMessage(String myId, String id, String title, String content) {
		return mapper.getSendMessage(myId, id, title, content);
	}
	
	// 쪽지 보내기 (SendMessageBox)
	@Override
	public Integer getSendMessage2(String myId2, String id2, String title2, String content2) {
		return mapper.getSendMessage2(myId2, id2, title2, content2);
	}
	
	// 쪽지 내용 확인
	@Override
	public List<MessageBox> getReadMyMessage(String id) {
		return mapper.getReadMyMessage(id);
	}
	
	// 쪽지 읽음 표시로 바꾸기 (messageBox)
	@Override
	public int getUpdateReadMyMessage(String id) {
		return mapper.getUpdateReadMyMessage(id);
	}
	
	// 쪽지 읽음 표시로 바꾸기 (SendMessageBox)
	@Override
	public int getUpdateReadMyMessage2(String id) {
		return mapper.getUpdateReadMyMessage2(id);
	}
	
	// 선택한 회원 정보 불러오기
	@Override
	public List<Member> getMember(String id) {
		return mapper.getMemberList(id);
	}

	// 회원 정보 수정 (S)
	@Override
	public Integer getUpdateinfo(Member member) {
		return mapper.getUpdateinfo(member);
	}

	// 회원 정보 수정 (B)
	@Override
	public Integer getUpdateinfoB(Member member) {
		return mapper.getUpdateinfoB(member);
	}
	
	// 회원 정보 모두 부르기
	@Override
	public List<Member> getAllMember() {
		return mapper.getAllMember();
	}

	// 회원 프로필 이미지 update
	@Override
	public Integer getUpdateImg(String image, String id) {
		return mapper.getUpdateImg(image, id);
	}
	
	// 닉네임으로 회원 정보 불러오기	
	@Override
	public List<Member> getNickNameMember(String nickname) {
		return mapper.getNickNameMember(nickname);
	}

	// 쪽지 삭제하기 (내가 받은 쪽지)
	@Override
	public Integer getDeleteMessage(String id) {
		return mapper.getDeleteMessage(id);
	}

	// 쪽지 삭제하기 (내가 보낸 쪽지)
	@Override
	public Integer getDeleteSendMessage(String id) {
		return mapper.getDeleteSendMessage(id);
	}
	


}

package com.ezen.farmocean.mypage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.dto.SellMember;
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

	@Override
	public List<Member> getMember(String id) {
		return mapper.getMemberList(id);
	}




}

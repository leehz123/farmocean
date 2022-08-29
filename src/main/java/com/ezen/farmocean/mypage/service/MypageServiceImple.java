package com.ezen.farmocean.mypage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.mypage.dto.MessageBox;
import com.ezen.farmocean.mypage.mapper.MypageMapper;

@Service
public class MypageServiceImple implements MypageService {

	MypageMapper mapper;
	
	public MypageServiceImple(MypageMapper mapper) {
		this.mapper = mapper;
	}
	
	// ���� ��ü
	@Override
	public List<MessageBox> getList() {
		return mapper.getList();
	}

	// ���� ���� ���� �� ��
	@Override
	public List<MessageBox> getMyList(String id) {
		return mapper.getMyList(id);
	}
	
	// ���� ���� ���� �� ��
	@Override
	public List<MessageBox> getMySendList(String id) {
		return mapper.getMySendList(id);
	}

	// ȸ�� ���� �ҷ�����
	@Override
	public List<Member> getMember(String id) {
		return mapper.getMemberList(id);
	}

	// ȸ�� ���� ����
	@Override
	public Integer getUpdateinfo(Member member) {
		return mapper.getUpdateinfo(member);
	}

	@Override
	public List<Member> getAllMemberList() {
		return mapper.getAllMemberList();
	}





}

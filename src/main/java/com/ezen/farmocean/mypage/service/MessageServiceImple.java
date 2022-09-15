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

	// ���� ������ (MessageBox)
	@Override
	public Integer getSendMessage(String myId, String id, String title, String content) {
		return mapper.getSendMessage(myId, id, title, content);
	}
	
	// ���� ������ (SendMessageBox)
	@Override
	public Integer getSendMessage2(String myId2, String id2, String title2, String content2) {
		return mapper.getSendMessage2(myId2, id2, title2, content2);
	}
	
	// ���� ���� Ȯ��
	@Override
	public List<MessageBox> getReadMyMessage(String id) {
		return mapper.getReadMyMessage(id);
	}
	
	// ���� ���� ǥ�÷� �ٲٱ� (messageBox)
	@Override
	public int getUpdateReadMyMessage(String id) {
		return mapper.getUpdateReadMyMessage(id);
	}
	
	// ���� ���� ǥ�÷� �ٲٱ� (SendMessageBox)
	@Override
	public int getUpdateReadMyMessage2(String id) {
		return mapper.getUpdateReadMyMessage2(id);
	}
	
	// ������ ȸ�� ���� �ҷ�����
	@Override
	public List<Member> getMember(String id) {
		return mapper.getMemberList(id);
	}

	// ȸ�� ���� ���� (S)
	@Override
	public Integer getUpdateinfo(Member member) {
		return mapper.getUpdateinfo(member);
	}

	// ȸ�� ���� ���� (B)
	@Override
	public Integer getUpdateinfoB(Member member) {
		return mapper.getUpdateinfoB(member);
	}
	
	// ȸ�� ���� ��� �θ���
	@Override
	public List<Member> getAllMember() {
		return mapper.getAllMember();
	}

	// ȸ�� ������ �̹��� update
	@Override
	public Integer getUpdateImg(String image, String id) {
		return mapper.getUpdateImg(image, id);
	}
	
	// �г������� ȸ�� ���� �ҷ�����	
	@Override
	public List<Member> getNickNameMember(String nickname) {
		return mapper.getNickNameMember(nickname);
	}

	// ���� �����ϱ� (���� ���� ����)
	@Override
	public Integer getDeleteMessage(String id) {
		return mapper.getDeleteMessage(id);
	}

	// ���� �����ϱ� (���� ���� ����)
	@Override
	public Integer getDeleteSendMessage(String id) {
		return mapper.getDeleteSendMessage(id);
	}
	


}

package com.ezen.farmocean.mypage.service;

import java.util.List;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.mypage.dto.MessageBox;

public interface MessageService {
	
	// ���� ��ü
	public List<MessageBox> getList();

	// ���� ���� ���� �� ��
	public List<MessageBox> getMyList(String id);
	
	// ���� ���� ���� �� ��
	public List<MessageBox> getMySendList(String id);
	
	// ���� ������ (messageBox)
	public Integer getSendMessage(String myId, String id, String title, String content);
	
	// ���� ������ (SendMessageBox)
	public Integer getSendMessage2(String myId2, String id2, String title2, String content2);
	
	// ���� ���� Ȯ��
	public List<MessageBox> getReadMyMessage(String id);
	
	// ���� ���� ǥ�÷� �ٲٱ� (messageBox)
	public int getUpdateReadMyMessage(String id);
	
	// ���� ���� ǥ�÷� �ٲٱ� (SendMessageBox)
	public int getUpdateReadMyMessage2(String id);
	
	// ȸ�� ���� �ҷ�����	
	public List<Member> getMember(String id);
	
	// �г������� ȸ�� ���� �ҷ�����
	public List<Member> getNickNameMember(String nickname);
	
	// ȸ�� ���� �����ϱ� (S)
	public Integer getUpdateinfo(Member member);
	
	// ȸ�� ���� �����ϱ� (B)
	public Integer getUpdateinfoB(Member member);
	
	// ȸ�� ���� ��� �ҷ�����
	public List<Member> getAllMember();
	
	// ȸ�� ������ �̹��� update
	public Integer getUpdateImg(String image, String id);

}

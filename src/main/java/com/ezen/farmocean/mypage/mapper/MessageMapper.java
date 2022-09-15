package com.ezen.farmocean.mypage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.mypage.dto.MessageBox;

public interface MessageMapper {
	
	// ���� ��ü
	public List<MessageBox> getList();
	
	// ���� ���� ���� �� ��
	public List<MessageBox> getMyList(String id);
	
	// ���� ���� ���� �� ��
	public List<MessageBox> getMySendList(String id);
	
	// ���� ������ (MessageBox)
	public Integer getSendMessage(@Param("myId") String myId, @Param("id") String id, @Param("title") String title, @Param("content") String content);
	
	// ���� ������ (SendMessageBox)
	public Integer getSendMessage2(@Param("myId2") String myId2, @Param("id2") String id2, @Param("title2") String title2, @Param("content2") String content2);
	
	// ���� ���� Ȯ��
	public List<MessageBox> getReadMyMessage(String id);
	
	// ���� ���� Ȯ��
	public List<MessageBox> getReadMyMessage2(String id);
	
	// ���� ���� ǥ�÷� �ٲٱ�
	public int getUpdateReadMyMessage(String id);
	
	// ���� ���� ǥ�÷� �ٲٱ� (SendMessageBox)
	public int getUpdateReadMyMessage2(String id);
	
	// ���� �����ϱ� (���� ���� ����)
	public Integer getDeleteMessage(String id);
		
	// ���� �����ϱ� (���� ���� ����)
	public Integer getDeleteSendMessage(String id);
	
	// ȸ�� ���� �ҷ�����
	public List<Member> getMemberList(String id);
	
	// �г������� ȸ�� ���� �ҷ�����
	public List<Member> getNickNameMember(String nickname);
	
	// ȸ�� ���� �����ϱ� (S)
	public int getUpdateinfo(Member member);
	
	// ȸ�� ���� �����ϱ� (B)
	public int getUpdateinfoB(Member member);
	
	// ȸ�� ���� ��� �ҷ�����
	public List<Member> getAllMember();

	// ȸ�� ������ �̹��� update
	public int getUpdateImg(@Param("image") String image, @Param("id") String id);
}

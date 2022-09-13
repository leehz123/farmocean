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
	
	// ���� ������
	public Integer getSendMessage(@Param("myId") String myId, @Param("id") String id, @Param("title") String title, @Param("content") String content);
	
	// ���� ���� Ȯ��
	public List<MessageBox> getReadMyMessage(String id);
	
	// ���� ���� ǥ�÷� �ٲٱ�
	public int getUpdateReadMyMessage(String id);
	
	// ȸ�� ���� �ҷ�����
	public List<Member> getMemberList(String id);
	
	// ȸ�� ���� �����ϱ� (S)
	public int getUpdateinfo(Member member);
	
	// ȸ�� ���� �����ϱ� (B)
	public int getUpdateinfoB(Member member);
	
	// ȸ�� ���� ��� �ҷ�����
	public List<Member> getAllMember();

	// ȸ�� ������ �̹��� update
	public int getUpdateImg(@Param("image") String image, @Param("id") String id);
}

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
	
	// ȸ�� ���� �ҷ�����	
	public List<Member> getMember(String id);
	
	// ȸ�� ���� �����ϱ� (S)
	public Integer getUpdateinfo(Member member);
	
	// ȸ�� ���� �����ϱ� (B)
	public Integer getUpdateinfoB(Member member);
	
	// ȸ�� ���� ��� �ҷ�����
	public List<Member> getAllMember();
	
	// ȸ�� ������ �̹��� update
	public Integer getUpdateImg(String image);

}

package com.ezen.farmocean.mypage.mapper;

import java.util.List;

import com.ezen.farmocean.mypage.dto.MessageBox;

public interface MessageMapper {
	
	// ���� ��ü
	public List<MessageBox> getList();
	
	// ���� ���͸�
	public List<MessageBox> getMyList(String id);

}

package com.ezen.farmocean.mypage.service;

import java.util.List;

import com.ezen.farmocean.mypage.dto.MessageBox;

public interface MessageService {
	
	// ���� ��ü
	public List<MessageBox> getList();

	// ���� ���͸�
	public List<MessageBox> getMyList(String id);

}

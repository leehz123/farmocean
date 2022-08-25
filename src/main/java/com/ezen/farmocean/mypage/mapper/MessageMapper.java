package com.ezen.farmocean.mypage.mapper;

import java.util.List;

import com.ezen.farmocean.mypage.dto.MessageBox;

public interface MessageMapper {
	
	// 쪽지 전체
	public List<MessageBox> getList();
	
	// 쪽지 내것만
	public List<MessageBox> getMyList(String id);

}

package com.ezen.farmocean.mainPage.service;

import java.util.List;

import com.ezen.farmocean.mainPage.dto.Cate;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.member.dto.Member;

public interface ProductListService {
	
	// ��ǰ �ֽż�
	public List<Product> getProcNewList();
				
	// ��ǰ �α��
	public List<Product> getProcPopList();
					
	// ��ǰ ī�װ� ��ü ���	
	
				
	// ��ǰ �� ���
	public List<Product> getProcBidsList();
	
	// �г���
	public List<Member> getMemberNick(String member_id);
	
}

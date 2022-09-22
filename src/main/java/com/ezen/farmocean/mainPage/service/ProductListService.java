package com.ezen.farmocean.mainPage.service;

import java.util.List;

import com.ezen.farmocean.mainPage.dto.Cate;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.dto.ProductView;
import com.ezen.farmocean.member.dto.Member;

public interface ProductListService {
	
	// ��ǰ �ֽż�
	public List<Product> getProcNewList();
				
	// ��ǰ �α��
	public List<Product> getProcPopList();
							
	// ��ǰ �� ���
	public List<Product> getProcBidsList();
	
	// ��ǰ ��� ���� �ֽż�
	public List<ProductView> getProcNewList2(String member_id); 
	
	// ��ǰ ��� ���� �α��
	public List<ProductView> getProcPopList2(String member_id); 
		
	// ��ǰ ��� ���� �� ���
	public List<ProductView> getProcBidsList2(String member_id); 
}

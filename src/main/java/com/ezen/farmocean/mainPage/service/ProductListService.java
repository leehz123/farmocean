package com.ezen.farmocean.mainPage.service;

import java.util.List;

import com.ezen.farmocean.mainPage.dto.Cate;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.dto.ProductView;
import com.ezen.farmocean.member.dto.Member;

public interface ProductListService {
	
	// 상품 최신순
	public List<Product> getProcNewList();
				
	// 상품 인기순
	public List<Product> getProcPopList();
							
	// 상품 찜 목록
	public List<Product> getProcBidsList();
	
	// 상품 멤버 조인 최신순
	public List<ProductView> getProcNewList2(String member_id); 
	
	// 상품 멤버 조인 인기순
	public List<ProductView> getProcPopList2(String member_id); 
		
	// 상품 멤버 조인 찜 목록
	public List<ProductView> getProcBidsList2(String member_id); 
}

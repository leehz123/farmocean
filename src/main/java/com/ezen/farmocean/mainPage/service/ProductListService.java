package com.ezen.farmocean.mainPage.service;

import java.util.List;

import com.ezen.farmocean.mainPage.dto.Cate;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.member.dto.Member;

public interface ProductListService {
	
	// 상품 최신순
	public List<Product> getProcNewList();
				
	// 상품 인기순
	public List<Product> getProcPopList();
					
	// 상품 카테고리 전체 목록	
	
				
	// 상품 찜 목록
	public List<Product> getProcBidsList();
	
	// 닉네임
	public List<Member> getMemberNick(String member_id);
	
}

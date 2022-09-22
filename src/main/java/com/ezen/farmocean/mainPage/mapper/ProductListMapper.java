package com.ezen.farmocean.mainPage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.mainPage.dto.Cate;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.dto.ProductView;
import com.ezen.farmocean.member.dto.Member;

public interface ProductListMapper {
	
//	// 상품 최신순
//	public List<Product> getProcNewList();
//		
//	// 상품 인기순
//	public List<Product> getProcPopList();
//			
//	// 상품 찜 목록
//	public List<Product> getProcBidsList();
	
	// 상품 멤버 조인 최신순
	public List<ProductView> getProcNewList2(String member_id); 
		
	// 상품 멤버 조인 인기순
	public List<ProductView> getProcPopList2(String member_id); 
			
	// 상품 멤버 조인 찜 목록
	public List<ProductView> getProcBidsList2(String member_id); 
	
	public List<Member> getMember(String member_id);

	public List<Member> getProfileImg(@Param("member_id") String member_id);
}

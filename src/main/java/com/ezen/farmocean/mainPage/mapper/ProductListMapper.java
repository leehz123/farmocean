package com.ezen.farmocean.mainPage.mapper;

import java.util.List;

import com.ezen.farmocean.mainPage.dto.Cate;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.member.dto.Member;

public interface ProductListMapper {
	
	// 상품 최신순
	public List<Product> getProcNewList();
		
	// 상품 인기순
	public List<Product> getProcPopList();
			
	// 상품 카테고리 전체 목록
			
	// 상품 찜 목록
	public List<Product> getProcBidsList();
	
	// 관리자 조회
	// 판매자 ID 검색
	public Member selMemberIdInfo(String member_id);
	// 판매자 닉네임 검색
	public Member selMemberNickInfo(String member_nickName);
		
	// 상품 조회
	// 판매자 ID 조회
	public List<Product> selProdIdInfo(String member_id);
	// 상품 번호
	public List<Product> selProdNumInfo(int prod_idx);
	// 상품 이름
	public List<Product> selProdNameInfo(String prod_name);
		
	// 카테고리 대분류
	public List<Integer> selCateTopList();
		
	// 카테고리 소분류
	public List<Cate> selCateSubList(Integer cate_main);

}

package com.ezen.farmocean.mainPage.mapper;

import java.util.List;

import com.ezen.farmocean.mainPage.dto.Cate;
import com.ezen.farmocean.mainPage.dto.Criteria;
import com.ezen.farmocean.mainPage.dto.Product;

public interface ProductMapper {
	
	// 상품 등록
	public void prodEnroll(Product prod);

	// 상품 목록
	public List<Product> prodsGetList(Criteria cri);
	
	// 상품 검색
	public List<Product> getProdsList(Criteria cri);
	
	// 상품 총 갯수
	public int prodsGetTotal(Criteria cri);
	
	// 판매자 id 리스트 요청
	public String[] getMemberIdList(String keyword);
	
	
}

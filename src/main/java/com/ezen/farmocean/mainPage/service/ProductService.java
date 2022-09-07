package com.ezen.farmocean.mainPage.service;

import java.util.List;

import com.ezen.farmocean.mainPage.dto.Criteria;
import com.ezen.farmocean.mainPage.dto.Product;

public interface ProductService {

	public void prodEnroll(Product prod);
	
	// 상품 리스트
	public List<Product> prodsGetList(Criteria cri);
	
	// 상품 검색
	public List<Product> getProdsList(Criteria cri);
	
	// 상품 총 갯수
	public int prodsGetTotal(Criteria cri);
	
}

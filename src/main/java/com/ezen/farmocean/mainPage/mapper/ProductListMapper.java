package com.ezen.farmocean.mainPage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.mainPage.dto.Cate;
import com.ezen.farmocean.mainPage.dto.Product;

public interface ProductListMapper {
	
	// 상품 최신순
	public List<Product> getProcNewList();
	
	// 상품 인기순
	public List<Product> getProcPopList();
	
	// 상품 카테고리 목록
	public List<Product> getProcCateList(String cate_idx);
//	public List<Cate> getProdCateList(String cate_main);
	
	// 상품 카테고리 전체 목록
	public List<Cate> getProcCateAllList1();
	public List<Cate> getProcCateAllList2();
	public List<Cate> getProcCateAllList3();
	public List<Cate> getProcCateAllList4();
	public List<Cate> getProcCateAllList5();
	public List<Cate> getProcCateAllList6();
	public List<Cate> getProcCateAllList7();
	
	// 상품 찜 목록
	public List<Product> getProcBidsList();	

}

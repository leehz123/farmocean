package com.ezen.farmocean.mainPage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.mainPage.dto.Cate;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.mapper.ProductListMapper;

@Service
public class ProductListServiceImpl implements ProductListService {
	
	@Autowired
	ProductListMapper productListmapper;

	@Override
	public List<Product> getProcNewList() {
		
		List<Product> list = productListmapper.getProcNewList();
		return list;
	}

	@Override
	public List<Product> getProcPopList() {
		return productListmapper.getProcPopList();
	}

	@Override
	public List<Product> getProcCateList(String cate_idx) {
		return productListmapper.getProcCateList(cate_idx);
	}
	
//	@Override
//	public List<Cate> getProdCateList(String cate_main) {
//		return productListmapper.getProdCateList(cate_main);
//	}
	
	

	@Override
	public List<Product> getProcBidsList() {
		return productListmapper.getProcBidsList();
	}

	@Override
	public List<Cate> getProcCateAllList1() {
		
		return productListmapper.getProcCateAllList1();
	}
	
	@Override
	public List<Cate> getProcCateAllList2() {
		
		return productListmapper.getProcCateAllList2();
	}
	
	@Override
	public List<Cate> getProcCateAllList3() {
		
		return productListmapper.getProcCateAllList3();
	}
	
	@Override
	public List<Cate> getProcCateAllList4() {
		
		return productListmapper.getProcCateAllList4();
	}
	
	@Override
	public List<Cate> getProcCateAllList5() {
		
		return productListmapper.getProcCateAllList5();
	}
	
	@Override
	public List<Cate> getProcCateAllList6() {
		
		return productListmapper.getProcCateAllList6();
	}
	
	@Override
	public List<Cate> getProcCateAllList7() {
		
		return productListmapper.getProcCateAllList7();
	}
	

}

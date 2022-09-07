package com.ezen.farmocean.mainPage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.mainPage.dto.Cate;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.mapper.ProductListMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProductListServiceImpl implements ProductListService{
	
	@Autowired
	private ProductListMapper prodListMapper;
	
	
	@Override
	public List<Product> getProcNewList() {
		
		List<Product> list = prodListMapper.getProcNewList();
		
		list.forEach(dto -> {
			int prodId = dto.getProd_idx();
			
			// 이미지 리스트
//			List<ProdImg> imgList = 
//			
//			dto.
		});
//		return mapper.getProcNewList();
		return list;
	}

	@Override
	public List<Product> getProcPopList() {
		return prodListMapper.getProcPopList();
	}

	@Override
	public List<Product> getProcBidsList() {
		return prodListMapper.getProcBidsList();
	}

	@Override
	public List<Cate> getProcCateAllList1() {
		
		return prodListMapper.getProcCateAllList1();
	}
	
	@Override
	public List<Cate> getProcCateAllList2() {
		
		return prodListMapper.getProcCateAllList2();
	}
	
	@Override
	public List<Cate> getProcCateAllList3() {
		
		return prodListMapper.getProcCateAllList3();
	}
	
	@Override
	public List<Cate> getProcCateAllList4() {
		
		return prodListMapper.getProcCateAllList4();
	}
	
	@Override
	public List<Cate> getProcCateAllList5() {
		
		return prodListMapper.getProcCateAllList5();
	}
	
	@Override
	public List<Cate> getProcCateAllList6() {
		
		return prodListMapper.getProcCateAllList6();
	}
	
	@Override
	public List<Cate> getProcCateAllList7() {
		
		return prodListMapper.getProcCateAllList7();
	}

}

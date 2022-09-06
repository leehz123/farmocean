package com.ezen.farmocean.mainPage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.mainPage.dto.Cate;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.mapper.JsonRestMapper;

@Service
public class JsonProdServiceImpl implements JsonProdService {
	
	@Autowired
	JsonRestMapper mapper;

	@Override
	public List<Product> getProcNewList() {
		
		List<Product> list = mapper.getProcNewList();
		
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
		return mapper.getProcPopList();
	}

	@Override
	public List<Product> getProcCateList(String cate_idx) {
		return mapper.getProcCateList(cate_idx);
	}

	@Override
	public Integer getProdBidsChk(Integer prod_idx, String member_id) {
		return mapper.getProdBidsChk(prod_idx, member_id);
	}

	@Override
	public Integer getProdUseChk(Integer prod_idx) {
		return mapper.getProdUseChk(prod_idx);
	}

	@Override
	public Integer setProdAddBids(Integer prod_idx, String member_id) {
		return mapper.setProdAddBids(prod_idx, member_id);
	}

	@Override
	public void setProdCntUpBids(Integer prod_idx) {
		mapper.setProdCntUpBids(prod_idx);
	}

	@Override
	public List<Product> getProcBidsList() {
		return mapper.getProcBidsList();
	}

	@Override
	public List<Cate> getProcCateAllList1() {
		
		return mapper.getProcCateAllList1();
	}
	
	@Override
	public List<Cate> getProcCateAllList2() {
		
		return mapper.getProcCateAllList2();
	}
	
	@Override
	public List<Cate> getProcCateAllList3() {
		
		return mapper.getProcCateAllList3();
	}
	
	@Override
	public List<Cate> getProcCateAllList4() {
		
		return mapper.getProcCateAllList4();
	}
	
	@Override
	public List<Cate> getProcCateAllList5() {
		
		return mapper.getProcCateAllList5();
	}
	
	@Override
	public List<Cate> getProcCateAllList6() {
		
		return mapper.getProcCateAllList6();
	}
	
	@Override
	public List<Cate> getProcCateAllList7() {
		
		return mapper.getProcCateAllList7();
	}
	

}

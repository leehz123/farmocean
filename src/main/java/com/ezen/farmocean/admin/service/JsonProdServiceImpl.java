package com.ezen.farmocean.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ezen.farmocean.admin.mapper.JsonRestMapper;
import com.ezen.farmocean.cs.dto.BoardCate;
import com.ezen.farmocean.cs.dto.CsBoard;
import com.ezen.farmocean.cs.mapper.BoardMapper;
import com.ezen.farmocean.prod.dto.Product;

@Service
public class JsonProdServiceImpl implements JsonProdService {
	
	@Autowired
	JsonRestMapper mapper;

	@Override
	public List<Product> getProcNewList() {
		return mapper.getProcNewList();
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

	

}

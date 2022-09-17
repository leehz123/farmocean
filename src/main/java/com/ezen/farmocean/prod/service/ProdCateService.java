package com.ezen.farmocean.prod.service;

import java.util.List;

import com.ezen.farmocean.prod.dto.Cate;

public interface ProdCateService {

	public List<Cate> getCateList();
	
	public String getCateName(Integer cate_idx);
}

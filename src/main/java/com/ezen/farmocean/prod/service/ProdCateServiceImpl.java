package com.ezen.farmocean.prod.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.prod.dto.Cate;
import com.ezen.farmocean.prod.mapper.ProdCateMapper;

@Service
public class ProdCateServiceImpl implements ProdCateService {

	@Autowired
	ProdCateMapper cateMapper;
	
	@Override
	public List<Cate> getCateList() {
		return cateMapper.getCateList();
	}
	
}

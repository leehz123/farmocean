package com.ezen.farmocean.prod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.prod.mapper.ProdMapper;

@Service
public class ProdServiceImpl implements ProdService {

	@Autowired
	ProdMapper productMapper;
	
	@Override
	public Integer insertProd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertProdExceptCate() {
		// TODO Auto-generated method stub
		return null;
	}

}

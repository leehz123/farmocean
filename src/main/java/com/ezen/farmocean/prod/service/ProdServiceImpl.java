package com.ezen.farmocean.prod.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.prod.dto.Product;
import com.ezen.farmocean.prod.mapper.ProdImgMapper;
import com.ezen.farmocean.prod.mapper.ProdMapper;

@Service
public class ProdServiceImpl implements ProdService {

	@Autowired
	ProdMapper productMapper;
	
	@Override
	public List<Product> getProductList() {
		return productMapper.getProductList();
	}

	@Override
	public Product getProductById(Integer id) {
		return productMapper.getProductById(id);
	}

	@Override
	public Integer insertProduct(String sellerId, String name, String info, String cate, String sell, Timestamp deadline) {
		return productMapper.insertProduct(sellerId, name, info, cate, sell, deadline);
	}

	@Override
	public Integer updateProduct(Integer id, String name, String info, String cate, String sell, Timestamp deadline) {
		return productMapper.updateProduct(id, name, info, cate, sell, deadline);
	}

	@Override
	public Integer deleteProductById(Integer id) {
		return productMapper.deleteProductById(id);
	}

	
	
	
}

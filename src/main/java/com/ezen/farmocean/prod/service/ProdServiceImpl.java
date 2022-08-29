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
	public Product getProductById(Integer prod_idx) {
		return productMapper.getProductById(prod_idx);
	}	

	@Override
	public Integer insertProduct(String member_id, String prod_name, String prod_info, Integer cate_idx,
			String prod_sell, String prod_price, Timestamp prod_sell_deadline) {
		return productMapper.insertProduct(member_id, prod_name, prod_info, cate_idx, prod_sell, prod_price, prod_sell_deadline);
	}

	@Override
	public Integer updateProduct(String prod_name, String prod_info, Integer cate_idx, String prod_sell,
			String prod_price, Timestamp prod_sell_deadline) {
		return productMapper.updateProduct(prod_name, prod_info, cate_idx, prod_sell, prod_price, prod_sell_deadline);
	}

	@Override
	public Integer deleteProductById(Integer prod_idx) {
		return productMapper.deleteProductById(prod_idx);
	}	


}

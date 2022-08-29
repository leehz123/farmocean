package com.ezen.farmocean.prod.service;

import java.sql.Timestamp;
import java.util.List;

import com.ezen.farmocean.prod.dto.Product;

public interface ProdService {

	public List<Product> getProductList();
	
	public Product getProductById(Integer id);
	
	public Integer insertProduct(String sellerId, String name, String info, String cate, String sell, Timestamp deadline);
	
	public Integer updateProduct(Integer id, String name, String info, String cate, String sell, Timestamp deadline);
	
	public Integer deleteProductById(Integer id);
	
}

package com.ezen.farmocean.prod.service;

import java.sql.Timestamp;
import java.util.List;

import com.ezen.farmocean.prod.dto.Product;

public interface ProdService {

	public List<Product> getProductList();
	
	public Product getProductById(Integer prod_idx);
	
	public Integer insertProduct(String member_id, String prod_name, String prod_info, Integer cate_idx, String prod_sell, String prod_price, Timestamp prod_sell_deadline);
	
	public Integer updateProduct(String prod_name, String prod_info, Integer cate_idx, String prod_sell, String prod_price, Timestamp prod_sell_deadline);
	
	public Integer deleteProductById(Integer prod_idx);
	
}

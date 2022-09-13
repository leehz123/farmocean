package com.ezen.farmocean.prod.service;

import java.sql.Timestamp;
import java.util.List;

import com.ezen.farmocean.prod.dto.Product;

public interface ProdService {

	public List<Product> getProductList();
	
	public Product getProductById(Integer prod_idx);
	
	public List<Product> getProductsByCate(Integer cate_idx);
	
	public List<Product> getProductsByMemberId(String member_id);
	
	public Integer insertProduct(String member_id, String prod_name, String prod_info, Integer cate_idx, String prod_sell, Integer prod_price, Timestamp prod_sell_deadline, Integer prod_stock, Integer prod_delete, Integer prod_heartnum);
	
	public Integer updateProduct(Integer prod_idx ,String prod_name, String prod_info, Integer cate_idx, String prod_sell, Integer prod_price, Timestamp prod_sell_deadline, Integer prod_stock, Integer prod_delete);
	
	public Integer deleteProductById(Integer prod_idx);

	public Integer updateProductDeleteToZeroByProdIdx(Integer prod_idx);
	
}

package com.ezen.farmocean.prod.mapper;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.Product;

public interface ProdMapper {

	public List<Product> getProductList();
	
	public Product getProductById(@Param("id") Integer id);
	
	public Integer insertProduct(	@Param("sellerId") String sellerId, 
									@Param("name") String name, 
									@Param("info") String info, 
									@Param("cate") String cate, 
									@Param("sell") String sell, 
									@Param("deadline") Timestamp deadline
								); 
	
	public Integer deleteProductById(@Param("id") Integer id);
	
	public Integer updateProduct(	@Param("id") Integer id, 
									@Param("name") String name, 
									@Param("info") String info, 
									@Param("cate") String cate, 
									@Param("sell") String sell, 
									@Param("deadline") Timestamp deadline
								); 
		
}


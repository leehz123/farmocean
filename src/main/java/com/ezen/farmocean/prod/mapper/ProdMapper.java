package com.ezen.farmocean.prod.mapper;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.Product;

public interface ProdMapper {

	public List<Product> getProductList();
	
	public Product getProductById(@Param("prod_idx") Integer prod_idx);
	
	public Integer insertProduct(	
									@Param("member_id") String member_id, 
									@Param("prod_name") String prod_name, 
									@Param("prod_info") String prod_info, 
									@Param("cate_idx") Integer cate_idx, 
									@Param("prod_sell") String prod_sell,
									@Param("prod_price") String prod_price,
									@Param("prod_sell_deadline") Timestamp prod_sell_deadline
								); 
	
	
	
	public Integer deleteProductById(@Param("prod_idx") Integer prod_idz);
	
	public Integer updateProduct(	 
									@Param("prod_name") String prod_name, 
									@Param("prod_info") String prod_info, 
									@Param("cate_idx") Integer cate_idx, 
									@Param("prod_sell") String prod_sell,
									@Param("prod_price") String  prod_price,
									@Param("prod_sell_deadline") Timestamp prod_sell_deadline
								);
	
	
}


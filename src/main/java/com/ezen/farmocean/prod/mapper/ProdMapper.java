package com.ezen.farmocean.prod.mapper;
import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;

public interface ProdMapper {

	public Integer insertProd(	
									@Param("seller") String seller, 
									@Param("name") String name, @Param("content") String content,
									@Param("cate") String cate, @Param("price") Integer price, 
									@Param("sell") String sell, @Param("deadline") Timestamp deadline
							); 
	
	
	public Integer insertProdExceptCate(
											@Param("seller") String seller, @Param("name") String name,
											@Param("content") String content, @Param("price") Integer price, 							
											@Param("sell") String sell, @Param("deadline") Timestamp deadline
										); 

		
	public Integer getIdxByName(@Param("name") String name);
	
	public String isDupliName(@Param("name") String name);
	
}

package com.ezen.farmocean.prod.mapper;
import java.sql.Date;

import org.apache.ibatis.annotations.Param;

public interface ProdMapper {

	public Integer insertProd(@Param("seller") String seller, @Param("name") String name,
			@Param("cate") String cate, @Param("img") String img, @Param("price") Integer price,
			@Param("from") String from, @Param("sell") String sell, @Param("deadline") Date deadline,
			@Param("content") String content); 
}

//#{seller}, #{name}, #{cate}, #{img}, #{price}, #{from}, #{sell}, #{deadline}, #{content}
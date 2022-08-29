package com.ezen.farmocean.prod.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.ProdImg;

public interface ProdImgMapper {
	
	public Integer insertImg(@Param("prod_idx") Integer prod_idx, @Param("img") String img);
		
	public List<ProdImg> getImgByProdId(@Param("prod_idx") Integer prod_idx);
}

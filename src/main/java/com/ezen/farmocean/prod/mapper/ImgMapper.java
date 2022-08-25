package com.ezen.farmocean.prod.mapper;

import org.apache.ibatis.annotations.Param;

public interface ImgMapper {
	
	public Integer insertImg(@Param("prod_idx") Integer prod_idx, @Param("img") String img);
		
}

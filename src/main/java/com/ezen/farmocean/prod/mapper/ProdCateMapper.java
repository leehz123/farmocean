package com.ezen.farmocean.prod.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.Cate;

public interface ProdCateMapper {

	public List<Cate> getCateList();

	public String getCateName(@Param("cate_idx") Integer cate_idx);
}

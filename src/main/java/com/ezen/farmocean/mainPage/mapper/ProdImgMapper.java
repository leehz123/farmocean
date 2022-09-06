package com.ezen.farmocean.mainPage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.mainPage.dto.ProdImg;

public interface ProdImgMapper {
	
	public List<ProdImg> getProdImgList();
	
	public List<ProdImg> getImgsByProdIdx(@Param("prod_idx") Integer prod_idx); //�� ��ǰ�� �̹����� �������� List�� �޾ƾ� ��
	
	public Integer insertImg(@Param("prod_idx") Integer prod_idx, @Param("img_url") String img_url);

	public Integer updateImgByImgIdx(@Param("img_idx") Integer prod_idx, @Param("img_url") String img_url);
	
	public Integer deleteImgByImgIdx(@Param("img_idx") Integer img_idx);
}



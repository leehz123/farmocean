package com.ezen.farmocean.prod.service;

import java.util.List;

import com.ezen.farmocean.prod.dto.ProdImg;

public interface ProdImgService {

	public List<ProdImg> getImgList();
	
	public List<ProdImg> getImgsByProdIdx(Integer id);
	
	public Integer insertProdImg(Integer prod_idx, String img_url);
	
	public Integer updateImgByImgIdx(Integer img_idx, String img_url);
	
	public Integer deleteProdImg(Integer img_idx);
}

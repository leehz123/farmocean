package com.ezen.farmocean.prod.service;

import java.util.List;

import com.ezen.farmocean.prod.dto.ProdImg;

public interface ProdImgService {

	public List<ProdImg> getImgByProdId(Integer id);
}

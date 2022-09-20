package com.ezen.farmocean.prod.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.prod.dto.ProdImg;
import com.ezen.farmocean.prod.mapper.ProdImgMapper;

@Service
public class ProdImgServiceImpl implements ProdImgService {

	@Autowired
	ProdImgMapper imgMapper;

	@Override
	public List<ProdImg> getImgList() {
		return imgMapper.getProdImgList();
	}

	@Override
	public List<ProdImg> getImgsByProdIdx(Integer prod_idx) {
		return imgMapper.getImgsByProdIdx(prod_idx);
	}

	@Override
	public Integer insertProdImg(Integer prod_idx, String img_url, Integer main_img) {
		return imgMapper.insertImg(prod_idx, img_url, main_img);
	}

	@Override
	public Integer updateImgByImgIdx(Integer img_idx, String img_url) {
		return imgMapper.updateImgByImgIdx(img_idx, img_url);
	}

	@Override
	public Integer deleteProdImg(Integer img_idx) {
		return imgMapper.deleteImgByImgIdx(img_idx);
	}

	@Override
	public Integer deleteProdImgByProd_idx(Integer prod_idx) {
		return imgMapper.deleteImgByProdIdx(prod_idx);
	}


}

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
	public List<ProdImg> getImgByProdId(Integer id) {
		
		return imgMapper.getImgByProdId(id);
	}

}

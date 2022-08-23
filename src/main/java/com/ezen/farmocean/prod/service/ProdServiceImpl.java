package com.ezen.farmocean.prod.service;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.prod.mapper.ProdMapper;

@Service
public class ProdServiceImpl implements ProdService {

	@Autowired
	ProdMapper productMapper;
	
	@Override
	public Integer insertProd() {
		LocalDate today = LocalDate.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf(today);
			
		return productMapper.insertProd("asdf", "test", "test", "test", 1000, "test", "∆«∏≈¡ﬂ", sqlDate, "test");
	}
	
}

package com.ezen.farmocean.mainPage.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.mainPage.dto.Criteria;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.mapper.ProductMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper prodMapper;
	
	@Override
	public void prodEnroll(Product prod) {
		
		log.info("(service)prodEnroll..");
		
		prodMapper.prodEnroll(prod);
	}
	
	@Override
	public List<Product> prodsGetList(Criteria cri) {

		log.info("prodsGetList().......");
		
		return prodMapper.prodsGetList(cri);
	}
	
	// »óÇ° °Ë»ö
	@Override
	public List<Product> getProdsList(Criteria cri) {

		log.info("getProdsList().......");
		
		String type = cri.getType();
		String[] typeArr = type.split("");
		
		for(String t : typeArr) {
			if(t.equals("A")) {
				String[] memberArr = prodMapper.getMemberIdList(cri.getKeyword());
				cri.setMember_id(memberArr);
			}
		}
		
		return prodMapper.getProdsList(cri);
	}
	
	// »óÇ° ÃÑ °¹¼ö
	@Override
	public int prodsGetTotal(Criteria cri) {
		
		log.info("prodsGetTotal().......");
		
		return prodMapper.prodsGetTotal(cri);
	}

}

package com.ezen.farmocean.mainPage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.mainPage.dto.Criteria;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.mapper.ProdImgMapper;
import com.ezen.farmocean.mainPage.mapper.ProdMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProdServiceImpl implements ProdService {
	
	@Autowired
	private ProdMapper prodMapper;
	
	@Autowired
	private ProdImgMapper prodImgMapper;
	
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
	
	// 상품 검색
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
	
	// 상품 총 갯수
	@Override
	public int prodsGetTotal(Criteria cri) {
		
		log.info("prodsGetTotal().......");
		
		return prodMapper.prodsGetTotal(cri);
	}
	
	// 상품 최신순
//	@Override
//	public List<NewProduct> getProdNewList() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}

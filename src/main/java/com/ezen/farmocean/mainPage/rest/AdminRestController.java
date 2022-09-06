package com.ezen.farmocean.mainPage.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.service.JsonProdService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class AdminRestController {
	
	@Autowired
	JsonProdService service;
	
	@Autowired
	HttpServletRequest req;
	
	/*
	 * 메인페이지
		1) 인기상품 - 찜 갯수 산정 베스트 8 상품 출력
		2) 인기키워드 - 가장 많이 설정되어 있는 키워드 알림 갯수 산정 10개 키워드 출력
		3) 최신순, 인기순 구별한 메인페이지 게시	[22.08.29] - 상품 DTO 수정 해야함
		4) 카테고리(채소, 과일, ...) 별 상품 조회가능 [22.08.29]
		5) 상품 게시글 작성, 수정, 삭제	 * 
	 * 
	 */
	
	/**
	 * 3) 최신순, 인기순 구별한 메인페이지 게시
	 * @param type N :  최신순 P : 인기순
	 * @return
	 */
	
	// 이거를 a태그로 카테처럼 넣으면 되는 거 아닌가?
	@GetMapping(value = "/prod/prodjsonlist/{type}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product> getProdNewList(@PathVariable char type){
		
		log.info(type);
		
		List<Product> prodList = new ArrayList<>();
		
		if(type == 'N' || type == 'n') {
			prodList = service.getProcNewList();
		}else {
			prodList = service.getProcPopList();
		}
		
		return prodList;
	}
	
	/**
	 * 4) 카테고리(채소, 과일, ...) 별 상품 조회가능 []
	 * @param prod_cate
	 * @return
	 */
	@GetMapping(value = "/prod/prodjsonlist/cate/{prod_cate}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product> getProdCateList(@PathVariable String prod_cate){
		
		List<Product> prodList = new ArrayList<>();
		
		prodList = service.getProcCateList(prod_cate);
		
		return prodList;
	}
	
	/**
	 * 1) 인기상품 - 찜 갯수 산정 베스트 8 상품 출력
	 * @return
	 */
	@GetMapping(value = "/prod/prodjsonlist/bids", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product> getProdBidsList(){
		
		List<Product> prodList = new ArrayList<>();
		
		prodList = service.getProcBidsList();
		
		return prodList;
	}
	
}

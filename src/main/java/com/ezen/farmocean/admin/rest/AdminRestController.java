package com.ezen.farmocean.admin.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.admin.service.JsonProdService;
import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.prod.dto.Product;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class AdminRestController {
	
	@Autowired
	JsonProdService service;
	
	@Autowired
	CommonFunction cf;
	
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
	 * @param type N :     최신순 P : 인기순
	 * @return
	 */
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
	
	
	@GetMapping(value = "/prod/prodaddbids/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> setAddProdBids(@PathVariable Integer prod_idx){
		
		LoginMember mInfo = cf.loginInfo(req);
		
		Map<String, String> result = new HashMap<>();
		
		if(mInfo.getMember_id() == null) {
			result.put("code", "-1");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			
			return result;
		}
		
		log.info(service.getProdUseChk(prod_idx));
		
		if(service.getProdUseChk(prod_idx) == 0) {
			result.put("code", "-6");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			
			return result;
		}
		
		if(service.getProdBidsChk(prod_idx, mInfo.getMember_id()) > 0) {
			result.put("code", "-5");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			
			return result;			
		}else {
			if(service.setProdAddBids(prod_idx, mInfo.getMember_id()) > 0) {
				service.setProdCntUpBids(prod_idx);
				result.put("code", "1");
				result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			}else {
				result.put("code", "-4");
				result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			}
		}
		
		
		
		return result;
	}
}

package com.ezen.farmocean.admin.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.cs.service.CommonFunction;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class AdminRestController {
	
	@Autowired
	CommonFunction cf;
	
	@Autowired
	HttpServletRequest req;
	
	/*
	 * 메인페이지
		1) 인기상품 - 찜 갯수 산정 베스트 8 상품 출력
		2) 인기키워드 - 가장 많이 설정되어 있는 키워드 알림 갯수 산정 10개 키워드 출력
		3) 최신순, 인기순 구별한 메인페이지 게시
		4) 카테고리(채소, 과일, ...) 별 상품 조회가능
		5) 상품 게시글 작성, 수정, 삭제	 * 
	 * 
	 */

}

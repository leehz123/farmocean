package com.ezen.farmocean.mainPage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.mainPage.dto.Criteria;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.dto.ProductView;
import com.ezen.farmocean.mainPage.mapper.ProductListMapper;
import com.ezen.farmocean.mainPage.service.ProductListService;
import com.ezen.farmocean.mainPage.service.ProductService;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.mypage.service.MessageService;
import com.ezen.farmocean.prod.dto.ProdImg;
import com.ezen.farmocean.prod.service.ProdCateServiceImpl;
import com.ezen.farmocean.prod.service.ProdImgServiceImpl;
import com.ezen.farmocean.prod.service.ProdServiceImpl;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Controller
//@RequestMapping("/mainpage")
public class MainPageController {
	
	@Autowired
	private ProductListService prodListService;
	
	@Autowired
	private CommonFunction cf;
	
	@Autowired
	MessageService service;
	
	@Autowired
	ProdImgServiceImpl iService;

	@GetMapping("/")
	public String mainPageGET(HttpSession session, Model model, String member_id, Integer prod_idx) {
		
//		log.info("메인페이지 진입");
		
		/* 상품 리스트 데이터 */
		// 상품 + 멤버 조인 찜 갯수 베스트 8
		List<ProductView> joinlist = prodListService.getProcBidsList(member_id);
		
		if(!joinlist.isEmpty()) {
			model.addAttribute("joinlist", joinlist);			
		}
		
		// 상품 + 멤버 조인 최신순 10
		List<ProductView> joinlist2 = prodListService.getProcNewList(member_id);
		
		if(!joinlist2.isEmpty()) {
			model.addAttribute("joinlist2", joinlist2);			
		}
		
		// 상품 + 멤버 조인 인기순 10
		List<ProductView> joinlist3 = prodListService.getProcPopList(member_id);
		
		if(!joinlist3.isEmpty()) {
			model.addAttribute("joinlist3", joinlist3);			
		}
		
		// 프로필 사진
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "/mainpage/main"; 
		} 
			LoginMember member = (LoginMember) session.getAttribute("loginId");
			model.addAttribute("memberinfo", service.getMember(member.getMember_id()));
		
//		List<ProdImg> imgList = iService.getImgsByProdIdx(prod_idx);
//		model.addAttribute("imgList", imgList);
		
		return "/mainpage/main";
				
	}

	
	
	
	
	
}

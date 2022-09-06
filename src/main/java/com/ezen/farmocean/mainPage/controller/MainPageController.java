package com.ezen.farmocean.mainPage.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.farmocean.mainPage.dto.Criteria;
import com.ezen.farmocean.mainPage.dto.PageDTO;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.service.JsonProdService;
import com.ezen.farmocean.mainPage.service.ProductService;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Controller
@RequestMapping("/mainpage")
public class MainPageController {
	
	@Autowired
	private JsonProdService service;
	
	@Autowired
	private HttpServletRequest req;
	
	@Autowired
	private ProductService prodService;

	@GetMapping("/main")
	public void mainPageGET(Criteria cri, Model model) {
		
		log.info("메인페이지 진입");
		
		model.addAttribute("cates1", service.getProcCateAllList1());
		model.addAttribute("cates2", service.getProcCateAllList2());
		model.addAttribute("cates3", service.getProcCateAllList3());
		model.addAttribute("cates4", service.getProcCateAllList4());
		model.addAttribute("cates5", service.getProcCateAllList5());
		model.addAttribute("cates6", service.getProcCateAllList6());
		model.addAttribute("cates7", service.getProcCateAllList7());
		
		// 찜 갯수 베스트 8 테스트
		/* 상품 리스트 데이터 */
		List list = service.getProcBidsList();
			
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}
		
		// 최신순 테스트
		List list2 = service.getProcNewList();
		
		if(!list2.isEmpty()) {
			model.addAttribute("list2", list2);
		} else {
			model.addAttribute("listCheck2", "empty");
			return;
		}
		
		// 인기순 테스트
		List list3 = service.getProcPopList();
				
		if(!list3.isEmpty()) {
			model.addAttribute("list3", list3);
		} else {
			model.addAttribute("listCheck3", "empty");
			return;
		}
				
	}
	
	/* 상품 검색 */
	@GetMapping("/search")
	public String searchGoodsGET(Criteria cri, Model model) {
		
		log.info("cri : " + cri);
		
		List<Product> list = prodService.getProdsList(cri);
		log.info("pre list : " + list);
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
			log.info("list : " + list); // 검색은 잘 되는데 페이지가 안 뜬다 아니다 안된다 ㅋ
		} else {
			model.addAttribute("listcheck", "empty");
			
			return "search";
		}
		
		model.addAttribute("pageMaker", new PageDTO(cri, prodService.prodsGetTotal(cri)));
		
		return "redirect:/mainpage/search";
		
		
	}
	
	
	
	
	
}

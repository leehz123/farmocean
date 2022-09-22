package com.ezen.farmocean.mainPage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.mainPage.dto.Criteria;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.dto.ProductView;
import com.ezen.farmocean.mainPage.service.ProductListService;
import com.ezen.farmocean.mainPage.service.ProductService;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.mypage.service.MessageService;

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

	@GetMapping("/")
	public String mainPageGET(HttpSession session, Model model, String member_id) {
		
//		log.info("���������� ����");
		
		/* ��ǰ ����Ʈ ������ */
		// �� ���� ����Ʈ 8
		List<Product> list = prodListService.getProcBidsList();
			
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		} 
		
		// ��ǰ + ��� ���� �� ���� ����Ʈ 8
		List<ProductView> joinlist = prodListService.getProcBidsList2(member_id);
		
		if(!joinlist.isEmpty()) {
			model.addAttribute("joinlist", joinlist);			
		}
		
		// �ֽż�
		List<Product> list2 = prodListService.getProcNewList();
		
		if(!list2.isEmpty()) {
			model.addAttribute("list2", list2);
		} 
		
		// ��ǰ + ��� ���� �ֽż� 10
		List<ProductView> joinlist2 = prodListService.getProcNewList2(member_id);
		
		if(!joinlist2.isEmpty()) {
			model.addAttribute("joinlist2", joinlist2);			
		}
		
		// �α�� 
		List<Product> list3 = prodListService.getProcPopList();
				
		if(!list3.isEmpty()) {
			model.addAttribute("list3", list3);
		} 
		
		// ��ǰ + ��� ���� �α�� 10
		List<ProductView> joinlist3 = prodListService.getProcPopList2(member_id);
		
		if(!joinlist3.isEmpty()) {
			model.addAttribute("joinlist3", joinlist3);			
		}
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "/mainpage/main"; 
		} 
			LoginMember member = (LoginMember) session.getAttribute("loginId");
			model.addAttribute("memberinfo", service.getMember(member.getMember_id()));	// ���ο����� ��� JSON���� ��
		
		
		return "/mainpage/main";
				
	}

	
	
	
	
	
}

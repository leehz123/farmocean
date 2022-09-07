package com.ezen.farmocean.mainPage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.farmocean.mainPage.dto.Criteria;
import com.ezen.farmocean.mainPage.dto.PageDTO;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.service.ProductService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/mainpage/prod_reg")
public class ProdRegController {
	
	@Autowired
	private ProductService prodService;
	
	// �Ǹ��� ���� ������ �̵�
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public void sellerMainGET() throws Exception{
		
		log.info("�Ǹ��� ��ǰ ������ �̵�");
	}
	
	// ��ǰ ��� ������ ����
	@RequestMapping(value = "goodsEnroll", method = RequestMethod.GET)
	public void goodsEnrollGET() throws Exception{
		
		log.info("��ǰ ��� ������ ����");
	}
	
	@PostMapping("/goodsEnroll")
	public String goodsEnrollPOST(Product prod, RedirectAttributes rttr) {
		
		log.info("goodsEnrollPOST...." + prod);
		
		prodService.prodEnroll(prod);
		
		rttr.addFlashAttribute("enroll_result", prod.getProd_name());
		
		return "redirect:/mainpage/prod_reg/goodsManage";
	}
	
	// ��ǰ ���� ������ ����
	@RequestMapping(value = "goodsManage", method = RequestMethod.GET)
	public void prodManageGET(Criteria cri, Model model) throws Exception{
			
		log.info("��ǰ ���� ������ ����");
				
		/* ��ǰ ����Ʈ ������ */
		List list = prodService.prodsGetList(cri);
			
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}
				
		/* ������ �������̽� ������ */
		model.addAttribute("pageMaker", new PageDTO(cri, prodService.prodsGetTotal(cri)));
	}

}

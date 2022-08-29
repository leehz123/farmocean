package com.ezen.farmocean.prod.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.service.MemberServiceImpl;
import com.ezen.farmocean.prod.dto.ProdImg;
import com.ezen.farmocean.prod.dto.Product;
import com.ezen.farmocean.prod.service.ProdImgService;
import com.ezen.farmocean.prod.service.ProdServiceImpl;

@RequestMapping("/product")
@Controller
public class prodController {

	@Autowired
	ProdServiceImpl pService;
	
	@Autowired
	ProdImgService iService;
	
	@Autowired
	MemberServiceImpl mService;
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String product_detail(Locale locale, Model model, @PathVariable("id") Integer id) {
	
		System.out.println(id);
		
		Product product = pService.getProductById(id);
		List<ProdImg> imgList = iService.getImgByProdId(id);
		Member member = mService.getMember(product.getSell_id());
		
		System.out.println(product.getProd_name());
		System.out.println(imgList.get(0).getImg_url());
		System.out.println(member.getMember_name());
		
		model.addAttribute("product", product);
		model.addAttribute("prodImg", imgList.get(0));
		model.addAttribute("member", member);
		
		return "product/product_detail";
	}
	
}

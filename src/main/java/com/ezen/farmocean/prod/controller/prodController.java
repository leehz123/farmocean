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
	
	@RequestMapping(value = "/detail/{prod_idx}", method = RequestMethod.GET)
	public String product_detail(Locale locale, Model model, @PathVariable("prod_idx") Integer prod_idx) {
	
		System.out.println(prod_idx);
		
		Product product = pService.getProductById(prod_idx);
		List<ProdImg> imgList = iService.getImgsByProdIdx(prod_idx);
		Member member = mService.getMember(product.getMember_id());
		
		System.out.println(product.getProd_name());
		System.out.println(imgList.get(0).getImg_url());
		System.out.println(member.getMember_name());
		
		model.addAttribute("product", product);
		model.addAttribute("prodImg", imgList.get(0));
		model.addAttribute("member", member);
		
		return "product/product_detail";
	}
	
}

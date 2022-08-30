package com.ezen.farmocean.prod.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.farmocean.member.dto.LoginMember;
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
	public String product_detail(Locale locale, Model model, HttpServletRequest req, @PathVariable("prod_idx") Integer prod_idx) {
	
//		System.out.println(prod_idx);
		
		Product product = pService.getProductById(prod_idx);
		List<ProdImg> imgList = iService.getImgsByProdIdx(prod_idx);
		Member member = mService.getMember(product.getMember_id());
		
		String prodInfo = product.getProd_info();
		if(prodInfo.substring(prodInfo.length()-6).equals("</div>")) {
			product.setProd_info("<div>" + prodInfo);
		}
		
		
//		System.out.println(product.getProd_name());
//		System.out.println(imgList.get(0).getImg_url());
//		System.out.println(member.getMember_name());

		
		model.addAttribute("product", product);
		model.addAttribute("prodImg", imgList.get(0));
		model.addAttribute("member", member);
		
		
		LoginMember loginMember = new LoginMember();
		HttpSession session = req.getSession();
		loginMember.setMember_id("kingbambbang");
		loginMember.setMember_name("ÀÌÈ¸¿ø");
		loginMember.setMember_nickName("¿Õ¹ã»§");
		loginMember.setMember_pw("asdf1234");
		loginMember.setMember_type("B");
	    session.setAttribute("loginId", loginMember);
		
		return "/product/product_detail";
	}
	
	
	@RequestMapping(value = "/list/{cate_idx}", method = RequestMethod.GET)
	public String product_list(Model model, @PathVariable("cate_idx") Integer cate_idx) {
		
		
		
		
		return "/product/product_list";
	}
	
}

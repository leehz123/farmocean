package com.ezen.farmocean.prod.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.service.MemberServiceImpl;
import com.ezen.farmocean.prod.dto.ProdImg;
import com.ezen.farmocean.prod.dto.Product;
import com.ezen.farmocean.prod.service.ProdCateServiceImpl;
import com.ezen.farmocean.prod.service.ProdImgServiceImpl;
import com.ezen.farmocean.prod.service.ProdServiceImpl;

@RequestMapping("/product")
@Controller
public class ProdController {

	@Autowired
	ProdServiceImpl pService;
	
	@Autowired
	ProdImgServiceImpl iService;
	
	@Autowired
	MemberServiceImpl mService;
	
	@Autowired
	ProdCateServiceImpl cService;
	
	
	@RequestMapping(value = "/detail/{prod_idx}", method = RequestMethod.GET)
	public String product_detail(Locale locale, Model model, HttpServletRequest req, @PathVariable("prod_idx") Integer prod_idx) {
		
//		System.out.println(prod_idx);

		//prod_delete가 1이면 home으로 가게..? http 상태 페이지 띄우거나
		
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
		
		
//		LoginMember loginMember = new LoginMember();
//		HttpSession session = req.getSession();
//		loginMember.setMember_id("kingbambbang");
//		loginMember.setMember_name("이회원");
//		loginMember.setMember_nickName("왕밤빵");
//		loginMember.setMember_pw("asdf1234");
//		loginMember.setMember_type("B");
//	    session.setAttribute("loginId", loginMember);
		
		return "/product/product_detail";
	}
	
	
	
	@RequestMapping(value = "/list/{cate_idx}/{page}", method = RequestMethod.GET)
	public String product_list(Model model, HttpServletRequest req, 
			@PathVariable("cate_idx") Integer cate_idx, @PathVariable("page") Integer page) {
		
		Integer prodNum = pService.getproductsByCate(cate_idx).size();		
		Integer cateNum = prodNum % 16 == 0 ? prodNum / 16 : prodNum / 16 + 1;
		model.addAttribute("cateNum", cateNum);		
		
		List<Product> allProductList = pService.getproductsByCate(cate_idx);
		List<Product> productList = new ArrayList<>();
		//page 별 표시할 상품의 리스트 인덱스 = 16*(page-1) ~ 16*(page-1)
		int beginIdx = 16 * (page-1);
		int endIdx = (16*page);
		List<Integer> prodIdxList = new ArrayList<>();
		int productListIdx = 0;
		for(int i = beginIdx; i < endIdx; ++i) {
			try {
				productList.add(allProductList.get(i));
			} catch(Exception e) {
				System.out.println(e.getMessage());
				break;
			}
			prodIdxList.add(productList.get(productListIdx).getProd_idx());
			++productListIdx;
		}
		model.addAttribute("productList", productList);
			   
		List<String> mainImgList = new ArrayList<>();
		for(Integer prodIdx : prodIdxList) {
			mainImgList.add(iService.getImgsByProdIdx(prodIdx).get(0).getImg_url());
		}
		model.addAttribute("mainImgList", mainImgList);
				
		return "/product/product_list";
	}

	
	//여기 작업중 팝업 띄울 때 패스 뭐로 입력해야할지 생각하셈
	@RequestMapping(value = "/product_review_write/{prod_idx}/{member_id}", method = RequestMethod.GET)
	public String product_review_write(Model model, HttpServletRequest req, 
			@PathVariable("prod_idx") Integer prod_idx, @PathVariable("member_id") String member_id) {

		
		
		
		return "/product/product_review_write";
	}
	

	@RequestMapping(value = "/product_detail_write", method = RequestMethod.GET)
	public String product_detail_write(Model model, HttpServletRequest req) {

		req.setAttribute("cateList", cService.getCateList());
		
		
		return "/product/product_detail_write";
	}

	
	

	
	   @GetMapping(value="/prod/insert_product/{uploadFolder}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Map<String, String> insertProduct(HttpServletRequest req,HttpServletResponse resp, MultipartHttpServletRequest multiFile, CommonFunction cf,
	            					@RequestParam MultipartFile upload, @PathVariable String uploadFolder) throws Exception {
		   
		   //결과 값 받을 String, String 맵
		   Map<String, String> result = new HashMap<>();
			
		   	//현재 로그인 중인 아이디
			LoginMember mInfo = cf.loginInfo(req);
			
			//현재 로그인 중인 아이디가 없을 경우 안전장치
			if(mInfo.getMember_id() == null) {
				result.put("filename", "");
	            result.put("uploaded", "0");
	            result.put("url", "");      
				
				return result;
			}
		   
			
			// 랜덤 문자 생성		
	        UUID uid = UUID.randomUUID();
	        
	        //아웃풋스트림 준비
	        OutputStream out = null;
	        
	        //인코딩
	        req.setCharacterEncoding("UTF-8");
	        resp.setCharacterEncoding("UTF-8");
	        resp.setContentType("text/html;charset=UTF-8");

	        
		   return null;
	   }

	
}

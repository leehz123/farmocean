package com.ezen.farmocean.prod.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.service.MemberServiceImpl;
import com.ezen.farmocean.prod.dto.ProdImg;
import com.ezen.farmocean.prod.dto.Product;
import com.ezen.farmocean.prod.service.ProdCateServiceImpl;
import com.ezen.farmocean.prod.service.ProdImgServiceImpl;
import com.ezen.farmocean.prod.service.ProdServiceImpl;

import lombok.extern.log4j.Log4j2;

@Log4j2
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
	
	@Autowired
	HttpSession session;

	
	
	@RequestMapping(value = {"/detail", "/detail/"}, method = RequestMethod.GET)
	public String product_detail() {
		
		return "redirect:/product/detail/2558";
	}

	
	@RequestMapping(value = {"/list/{cate_idx}", "/list/{cate_idx}/"}, method = RequestMethod.GET)
	public String product_list(@PathVariable("cate_idx") Integer cate_idx) {
		
		return "redirect:/product/list/" + cate_idx + "/1";
	}

	@RequestMapping(value = {"/list/seller/{member_id}", "/list/{member_id}/"}, method = RequestMethod.GET)
	public String seller_product_list_page1(@PathVariable("member_id") String member_id) {
		
		return "redirect:/product/list/seller/" + member_id + "/1";
	}
	
	

	
	
	
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
	
	
	//카테고리와 페이지에 따라 product_list.jsp에 상품 목록 띄우기
	@RequestMapping(value = "/list/{cate_idx}/{page}", method = RequestMethod.GET)
	public String product_list(Model model, HttpServletRequest req, 
			@PathVariable("cate_idx") Integer cate_idx, @PathVariable("page") Integer page) {
		
		Integer prodNum = pService.getProductsByCate(cate_idx).size();		
		Integer pageNum = prodNum % 16 == 0 ? prodNum / 16 : prodNum / 16 + 1;
		model.addAttribute("pageNum", pageNum);		
		
		List<Product> allProductList = pService.getProductsByCate(cate_idx);
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
	
	
	
	//판매자 아이디와 페이지에 따라 product_list.jsp에 상품 목록 띄우기
	@RequestMapping(value = "/list/seller/{member_id}/{page}", method = RequestMethod.GET)
	public String seller_product_list(Model model, HttpServletRequest req, 
			@PathVariable("member_id") String member_id, @PathVariable("page") Integer page) {
		
		Integer prodNum = pService.getProductsByMemberId(member_id).size();		
		Integer pageNum = prodNum % 16 == 0 ? prodNum / 16 : prodNum / 16 + 1;
		model.addAttribute("pageNum", pageNum);		
		
		List<Product> allProductList = pService.getProductsByMemberId(member_id);
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
	@RequestMapping(value = "/product_review_write/{prod_idx}", method = RequestMethod.GET)
	public String product_review_write(Model model, HttpServletRequest req, 
			@PathVariable("prod_idx") Integer prod_idx) {

		return "/product/product_review_write";
	}
	

	@RequestMapping(value = "/product_detail_write", method = RequestMethod.GET)
	public String product_detail_write(Model model) {
		model.addAttribute("cateList", cService.getCateList());
		return "/product/product_detail_write";
	}


	@RequestMapping(value = "/product_detail_edit/{prod_idx}", method = RequestMethod.GET)
	public String product_detail_edit(Model model, HttpServletRequest req, @PathVariable Integer prod_idx) {
		model.addAttribute("cateList", cService.getCateList());
		return "/product/product_detail_edit";
	}

	
	//http://localhost:8888/farmocean/product/insert_prod
	@RequestMapping(value = "/insert_prod", method = RequestMethod.POST)
	public String insert_prod(Model model, HttpServletRequest req, Product product) throws ParseException {

        String inDate = req.getParameter("deadline").replace('T', ' ');//2022-09-12T14:02에서 T 뺴고 스페이스로 대체
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = df.parse(inDate);
        long time = date.getTime();
        Timestamp prod_sell_deadline = new Timestamp(time);
        
        Date today = new Date();
        String prod_sell = "";
        int result = prod_sell_deadline.compareTo(today);

        if(result == 0) {
            System.out.println("딱 지금 판매종료");
        	prod_sell = "판매종료";
        } else if (result < 0) {
            System.out.println("판매종료");
        	prod_sell = "판매종료";
        } else {
        	System.out.println("판매중");
        	prod_sell = "판매중";
        }
        	        
        LoginMember member = (LoginMember) session.getAttribute("loginId");
        String member_id = member.getMember_id();
        String prod_name = product.getProd_name();

        String prod_info = null;
        String inputContent = product.getProd_info();
        if(inputContent != null) {
        	inputContent.replace("<p>", "");
            inputContent.replace("</p>", "");
            inputContent.replace("&nbsp;", "");
        }

        if(inputContent.length() < 1 || inputContent == null || inputContent.equals("")) {
        	prod_info = "<p>상품 상세 내용 준비 중입니다.</p>";
        } else {
        	prod_info = product.getProd_info();
        }
        
        Integer prod_price = product.getProd_price();
        Integer prod_stock = product.getProd_stock();
        Integer cate_idx = product.getCate_idx();
        
        
        pService.insertProduct(member_id, prod_name, prod_info, cate_idx, prod_sell, prod_price, prod_sell_deadline, prod_stock, 0, 0);
        
		return "/product/product_detail_write";//임시 리턴값. prod_idx에 해당하는 상세페이지로 이동해야 됨 
	}	

	//http://localhost:8888/farmocean/product/update_prod
	@RequestMapping(value = "/update_prod", method = RequestMethod.POST)
	public String update_prod(Model model, HttpServletRequest req, Product product) throws ParseException {
		System.out.println("컨트롤러에 오긴 오니?");
		System.out.println(product);
		
		String str = req.getRequestURL().toString().replace("/farmocean/product/product_detail_edit/", "");
		
        String inDate = req.getParameter("deadline").replace('T', ' ');//2022-09-12T14:02에서 T 뺴고 스페이스로 대체
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = df.parse(inDate);
        long time = date.getTime();
        Timestamp prod_sell_deadline = new Timestamp(time);
        
        Date today = new Date();
        String prod_sell = "";
        int result = prod_sell_deadline.compareTo(today);

        if(result == 0) {
            System.out.println("딱 지금 판매종료");
        	prod_sell = "판매종료";
        } else if (result < 0) {
            System.out.println("판매종료");
        	prod_sell = "판매종료";
        } else {
        	System.out.println("판매중");
        	prod_sell = "판매중";
        }
        	        
        LoginMember member = (LoginMember) session.getAttribute("loginId");
        String member_id = member.getMember_id();
        String prod_name = product.getProd_name();
        
        String prod_info = product.getProd_info(); 
//        String prod_info = null;
//        String inputContent = product.getProd_info();
//        if(inputContent != null) {
//        	inputContent.replace("<p>", "");
//            inputContent.replace("</p>", "");
//            inputContent.replace("&nbsp;", "");
//        }
//        if(inputContent.length() < 1 || inputContent == null || inputContent.equals("")) {
//        	prod_info = "<p>상품 상세 내용 준비 중입니다.</p>";
//        } else {
//        	prod_info = product.getProd_info();
//        }
        Integer prod_idx = product.getProd_idx();
        Integer prod_price = product.getProd_price();
        Integer prod_stock = product.getProd_stock();
        Integer cate_idx = product.getCate_idx();
        
        pService.updateProduct(prod_idx, prod_name, prod_info, cate_idx, prod_sell, prod_price, prod_sell_deadline, prod_stock, 0);
		
		return "/product/product_detail_write";//임시 리턴값. prod_idx에 해당하는 상세페이지로 이동해야 됨
	}

	
	
	//http://localhost:8888/farmocean/product/delete_prod
	@RequestMapping(value = "/delete_prod/{prod_idx}", method = RequestMethod.PUT) //update문 쓸 거라 DELETE 아니고 PUT임 주의
	public String delete_prod(Model model, HttpServletRequest req, @PathVariable Integer prod_idx) {

		//데이터를 정말 삭제하는 건 아니고 prod_delete 를 1에서 0으로 바꿔서 삭제한 척만 하는 거
		pService.updateProductDeleteToZeroByProdIdx(prod_idx);
		
		return "/product/product_detail_write"; //임시 리턴값. 얘는 판매자의 상품 게시글 목록으로 가야 됨
	}

	
	
	
}

package com.ezen.farmocean.prod.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import com.ezen.farmocean.prod.mapper.EtcMapper;
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

    @Autowired
    EtcMapper etcMapper;

    static final int prodNumPerPage = 15;
	
	
	@RequestMapping(value = {"/detail", "/detail/"}, method = RequestMethod.GET)
	public String product_detail() {
		
		return "redirect:/product/detail/2558";
	}

	
	@RequestMapping(value = {"/list/{cate_idx}", "/list/{cate_idx}/"}, method = RequestMethod.GET)
	public String product_list(@PathVariable("cate_idx") Integer cate_idx) {
		
		return "redirect:/product/list/" + cate_idx + "/1";
	}

	@RequestMapping(value = {"/list/seller/{member_id}", "/list/seller/{member_id}/"}, method = RequestMethod.GET)
	public String seller_product_list_page1(@PathVariable("member_id") String member_id) {
		
		return "redirect:/product/list/seller/" + member_id + "/1";
	}
	
	@RequestMapping(value = {"/list/name/{prod_name}", "/list/name/{prod_name}/"}, method = RequestMethod.GET)
	public String name_product_list_page1(@PathVariable("prod_name") String prod_name) throws UnsupportedEncodingException {
		
		String encodedname = URLEncoder.encode(prod_name, "UTF-8");
		encodedname.replaceAll("\\+", "%20");
		
		return "redirect:/product/list/name/" + encodedname + "/1";
	}
		
	@RequestMapping(value = {"/list/seller_nick/{member_nickname}", "/list/seller_nick/{member_nickname}/"}, method = RequestMethod.GET)
	public String seller_nick_product_list_page1(@PathVariable("member_nickname") String member_nickname) throws UnsupportedEncodingException {
		
		String encodednick = URLEncoder.encode(member_nickname, "UTF-8");
		return "redirect:/product/list/seller_nick/" + encodednick + "/1";
	}
	
	
	@RequestMapping(value = "/detail/{prod_idx}", method = RequestMethod.GET)
	public String product_detail(Locale locale, Model model, HttpServletRequest req, @PathVariable("prod_idx") Integer prod_idx) {
		
//		System.out.println(prod_idx);

		//�������������� prod_delete�� 0�� �ƴϸ� ���â �߰� �ؾ� �� try- catch ������ ���α�
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
		try {
			model.addAttribute("prodImg", imgList);
		} catch(Exception e) {
			//��ǰ ����� �̹��� ����		
		}
		model.addAttribute("member", member);
		
		
//		LoginMember loginMember = new LoginMember();
//		HttpSession session = req.getSession();
//		loginMember.setMember_id("kingbambbang");
//		loginMember.setMember_name("��ȸ��");
//		loginMember.setMember_nickName("�չ㻧");
//		loginMember.setMember_pw("asdf1234");
//		loginMember.setMember_type("B");
//	    session.setAttribute("loginId", loginMember);
		
		return "/product/product_detail";
	}
	
	
	
	//ī�װ��� �������� ���� product_list.jsp�� ��ǰ ��� ����
	@RequestMapping(value = "/list/{cate_idx}/{page}", method = RequestMethod.GET)
	public String product_list(Model model, HttpServletRequest req, 
			@PathVariable("cate_idx") Integer cate_idx, @PathVariable("page") Integer page) {
		
		List<Product> allProductList = pService.getProductsByCate(cate_idx);
		model.addAttribute("sort", cService.getCateName(cate_idx));
		
		Integer prodNum = allProductList.size();		
		Integer pageNum = prodNum % prodNumPerPage == 0 ? prodNum / prodNumPerPage : prodNum / prodNumPerPage + 1;
		model.addAttribute("pageNum", pageNum);		
		
		List<Product> productList = new ArrayList<>();
		//page �� ǥ���� ��ǰ�� ����Ʈ �ε��� = prodNumPerPage*(page-1) ~ prodNumPerPage*(page-1)
		int beginIdx = prodNumPerPage * (page-1);
		int endIdx = (prodNumPerPage*page);
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
			ProdImg productFirstImg = null;
			try {
				productFirstImg = iService.getImgsByProdIdx(prodIdx).get(0);
			} catch (Exception e) {
			}
			if(productFirstImg == null) {				
				mainImgList.add("http://localhost:8888/farmocean/resources/upload/prod_img/34a828af-e0cc-4aa6-a807-769d253b56dc.jpg");
			} else {				
				mainImgList.add(productFirstImg.getImg_url()); //��ǰ �̹��� ���� �� ���⼭ ���� �߻�
			}
		}
		model.addAttribute("mainImgList", mainImgList);
		model.addAttribute("searchCondition", "cate");		
		
		return "/product/product_list";
	}
	
	
	
	//�Ǹ��� ���̵�� �������� ���� product_list.jsp�� ��ǰ ��� ����
	@RequestMapping(value = "/list/seller/{member_id}/{page}", method = RequestMethod.GET)
	public String seller_product_list(Model model, HttpServletRequest req, 
			@PathVariable("member_id") String member_id, @PathVariable("page") Integer page) {
		
		List<Product> allProductList = pService.getProductsByMemberId(member_id);

		Integer prodNum = allProductList.size();		
		Integer pageNum = prodNum % prodNumPerPage == 0 ? prodNum / prodNumPerPage : prodNum / prodNumPerPage + 1;
		model.addAttribute("pageNum", pageNum);		
		model.addAttribute("sort", "\"" + member_id + "\" �˻� ���");
		
		List<Product> productList = new ArrayList<>();
		//page �� ǥ���� ��ǰ�� ����Ʈ �ε��� = prodNumPerPage*(page-1) ~ prodNumPerPage*(page-1)
		int beginIdx = prodNumPerPage * (page-1);
		int endIdx = (prodNumPerPage*page);
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
			ProdImg productFirstImg = null;
			try {
				productFirstImg = iService.getImgsByProdIdx(prodIdx).get(0);
			} catch (Exception e) {
			}
			if(productFirstImg == null) {				
				mainImgList.add("http://localhost:8888/farmocean/resources/upload/prod_img/34a828af-e0cc-4aa6-a807-769d253b56dc.jpg");
			} else {				
				mainImgList.add(productFirstImg.getImg_url()); //��ǰ �̹��� ���� �� ���⼭ ���� �߻�
			}
		}
		model.addAttribute("mainImgList", mainImgList);
		model.addAttribute("searchCondition", "sellerId");
		
		return "/product/product_list";
	}

	
	//��ǰ �̸��� �������� ���� product_list.jsp�� ��ǰ ��� ���� (��ǰ �̸��� = �� �ƴ� like '%#{prod_id}%'�� select)
	@RequestMapping(value = "/list/name/{prod_name}/{page}", method = RequestMethod.GET)
	public String name_product_list(Model model, HttpServletRequest req, 
			@PathVariable("prod_name") String prod_name, @PathVariable("page") Integer page) throws UnsupportedEncodingException {
		
		String prod_name_for_select = prod_name.replaceAll("\\+", " ");

		List<Product> allProductList = pService.getProductsByName(prod_name_for_select);
		
		Integer prodNum = allProductList.size();		
		Integer pageNum = prodNum % prodNumPerPage == 0 ? prodNum / prodNumPerPage : prodNum / prodNumPerPage + 1;
		model.addAttribute("pageNum", pageNum);		
		model.addAttribute("sort", "\"" + prod_name_for_select + "\" �˻� ���");
		
		List<Product> productList = new ArrayList<>();
		//page �� ǥ���� ��ǰ�� ����Ʈ �ε��� = prodNumPerPage*(page-1) ~ prodNumPerPage*(page-1)
		int beginIdx = prodNumPerPage * (page-1);
		int endIdx = (prodNumPerPage*page);
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
			ProdImg productFirstImg = null;
			try {
				productFirstImg = iService.getImgsByProdIdx(prodIdx).get(0);
			} catch (Exception e) {
			}
			if(productFirstImg == null) {				
				mainImgList.add("http://localhost:8888/farmocean/resources/upload/prod_img/34a828af-e0cc-4aa6-a807-769d253b56dc.jpg");
			} else {				
				mainImgList.add(productFirstImg.getImg_url()); //��ǰ �̹��� ���� �� ���⼭ ���� �߻�
			}
		}
		model.addAttribute("mainImgList", mainImgList);
		model.addAttribute("searchCondition", "prodName");
		model.addAttribute("serchName", prod_name_for_select);
		
		return "/product/product_list";
	}
	
	
	   
	//�Ǹ��� �г۰� product_list.jsp�� ��ǰ ��� ���� (�г۵� ���������� = �� �ƴ� like '%#{member_nickname}%'�� select)
	@RequestMapping(value = "/list/seller_nick/{member_nickname}/{page}", method = RequestMethod.GET)
	public String seller_nick_product_list(Model model, HttpServletRequest req, 
			@PathVariable("member_nickname") String member_nickname, @PathVariable("page") Integer page) throws UnsupportedEncodingException {
		
		
		//����г����� ��Ƶ� ��������
		String member_id = etcMapper.getMemberIdByNickname(member_nickname);
		
		List<Product> allProductList = pService.getProductsByMemberId(member_id);

		Integer prodNum = allProductList.size();		
		Integer pageNum = prodNum % prodNumPerPage == 0 ? prodNum / prodNumPerPage : prodNum / prodNumPerPage + 1;
		model.addAttribute("pageNum", pageNum);		
		model.addAttribute("sort", "\"" + member_nickname + "\" �˻����");
		
		List<Product> productList = new ArrayList<>();
		//page �� ǥ���� ��ǰ�� ����Ʈ �ε��� = prodNumPerPage*(page-1) ~ prodNumPerPage*(page-1)
		int beginIdx = prodNumPerPage * (page-1);
		int endIdx = (prodNumPerPage*page);
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
			ProdImg productFirstImg = null;
			try {
				productFirstImg = iService.getImgsByProdIdx(prodIdx).get(0);
			} catch (Exception e) {
			}
			if(productFirstImg == null) {				
				mainImgList.add("http://localhost:8888/farmocean/resources/upload/prod_img/34a828af-e0cc-4aa6-a807-769d253b56dc.jpg");
			} else {				
				mainImgList.add(productFirstImg.getImg_url()); //��ǰ �̹��� ���� �� ���⼭ ���� �߻�
			}
		}
		model.addAttribute("mainImgList", mainImgList);
		model.addAttribute("searchCondition", "sellerNickname");
		model.addAttribute("serchNick", member_nickname);
		
		return "/product/product_list";
	}
	

	

	
	//���� �۾��� �˾� ��� �� �н� ���� �Է��ؾ����� �����ϼ�
	@RequestMapping(value = "/product_review_write/{prod_idx}/{buy_idx}", method = RequestMethod.GET)
	public String product_review_write(
										Model model,  
										@PathVariable("prod_idx") Integer prod_idx,
										@PathVariable("buy_idx") Integer buy_idx) {
		model.addAttribute("buy_idx", buy_idx);
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

	
	//http://localhost:8888/farmocean/product/delete_prod
	@RequestMapping(value = "/delete_prod/{prod_idx}", method = RequestMethod.PUT) //update�� �� �Ŷ� DELETE �ƴϰ� PUT�� ����
	public String delete_prod(Model model, HttpServletRequest req, @PathVariable Integer prod_idx) {

		//�����͸� ���� �����ϴ� �� �ƴϰ� prod_delete �� 1���� 0���� �ٲ㼭 ������ ô�� �ϴ� ��
		pService.updateProductDeleteToZeroByProdIdx(prod_idx);
		
		return "/product/product_detail_write"; //�ӽ� ���ϰ�. ��� �Ǹ����� ��ǰ �Խñ� ������� ���� ��
	}

	
}

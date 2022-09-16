package com.ezen.farmocean.admin.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.admin.dto.Banner;
import com.ezen.farmocean.admin.dto.BuyInfo;
import com.ezen.farmocean.admin.dto.BuyListInfo;
import com.ezen.farmocean.admin.dto.MemberFaulty;
import com.ezen.farmocean.admin.dto.MemberFaultyInfo;
import com.ezen.farmocean.admin.service.JsonProdService;
import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.prod.dto.Cate;
import com.ezen.farmocean.prod.dto.Product;
import com.ezen.farmocean.prod.service.ProdService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class AdminRestController {
	
	@Autowired
	JsonProdService service;
	
	@Autowired
	CommonFunction cf;
	
	@Autowired
	HttpServletRequest req;
	
	@Autowired
	ProdService serviceProd;
	
	/*
	 * ����������
		1) �α��ǰ - �� ���� ���� ����Ʈ 8 ��ǰ ���
		2) �α�Ű���� - ���� ���� �����Ǿ� �ִ� Ű���� �˸� ���� ���� 10�� Ű���� ���
		3) �ֽż�, �α�� ������ ���������� �Խ�	[22.08.29] - ��ǰ DTO ���� �ؾ���
		4) ī�װ�(ä��, ����, ...) �� ��ǰ ��ȸ���� [22.08.29]
		5) ��ǰ �Խñ� �ۼ�, ����, ����	 * 
	 * 
	 */
	
	/**
	 * 3) �ֽż�, �α�� ������ ���������� �Խ�
	 * @param type N :     �ֽż� P : �α��
	 * @return
	 */
	@GetMapping(value = "/prod/prodjsonlist/{type}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product> getProdNewList(@PathVariable char type){
		
		log.info(type);
		
		List<Product> prodList = new ArrayList<>();
		
		if(type == 'N' || type == 'n') {
			prodList = service.getProcNewList();
		}else {
			prodList = service.getProcPopList();
		}
		
		return prodList;
	}
	
	/**
	 * 4) ī�װ�(ä��, ����, ...) �� ��ǰ ��ȸ���� []
	 * @param prod_cate
	 * @return
	 */
	@GetMapping(value = "/prod/prodjsonlist/cate/{prod_cate}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product> getProdCateList(@PathVariable String prod_cate){
		
		List<Product> prodList = new ArrayList<>();
		
		prodList = service.getProcCateList(prod_cate);
		
		return prodList;
	}
	
	/**
	 * 1) �α��ǰ - �� ���� ���� ����Ʈ 8 ��ǰ ���
	 * @return
	 */
	@GetMapping(value = "/prod/prodjsonlist/bids", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product> getProdBidsList(){
		
		List<Product> prodList = new ArrayList<>();
		
		prodList = service.getProcBidsList();
		
		return prodList;
	}
	
	
	/**
	 * ��ǰ ��	
	 * @param prod_idx
	 * @return
	 */
	@GetMapping(value = "/prod/prodaddbids/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> setAddProdBids(@PathVariable Integer prod_idx){
		
		LoginMember mInfo = cf.loginInfo(req);
		
		Map<String, String> result = new HashMap<>();
		
		if(mInfo.getMember_id() == null) {
			result.put("code", "-1");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			
			return result;
		}
		
//		log.info(service.getProdUseChk(prod_idx));
		
		if(service.getProdUseChk(prod_idx) == 0) {
			result.put("code", "-6");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			
			return result;
		}
		
		if(service.getProdBidsChk(prod_idx, mInfo.getMember_id()) > 0) {
			result.put("code", "-5");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			
			return result;			
		}else {
			if(service.setProdAddBids(prod_idx, mInfo.getMember_id()) > 0) {
				service.setProdCntUpBids(prod_idx, 1);
				result.put("code", "1");
				result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			}else {
				result.put("code", "-4");
				result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			}
		}
		
		return result;
	}
	
	/**
	 * �� ���
	 * @param prod_idx
	 * @return
	 */
	@GetMapping(value = "/prod/prodcancelbids/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> setCancelProdBids(@PathVariable Integer prod_idx){
		
		LoginMember mInfo = cf.loginInfo(req);
		
		Map<String, String> result = new HashMap<>();
		
		if(mInfo.getMember_id() == null) {
			result.put("code", "-1");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			
			return result;
		}
		
		log.info(service.getProdUseChk(prod_idx));
		
		if(service.getProdUseChk(prod_idx) == 0) {
			result.put("code", "-6");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			
			return result;
		}
		
		if(service.getProdBidsChk(prod_idx, mInfo.getMember_id()) > 0) {
			if(service.setProdCancelBids(prod_idx, mInfo.getMember_id()) > 0) {
				service.setProdCntUpBids(prod_idx, -1);
				result.put("code", "1");
				result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			}else {
				result.put("code", "-4");
				result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			}
			
			return result;			
		}else {
			result.put("code", "-6");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
		}
		
		return result;
	}
	
	@GetMapping(value = "/prodJson/cateTopList", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Integer> selCateTopList(){
		return service.selCateTopList();
	}
	
	@GetMapping(value = "/prodJson/cateSubList/{cate_main}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Cate> selCateSubList(@PathVariable Integer cate_main){
		return service.selCateSubList(cate_main);
	}
	
	@PostMapping(value = "/prodJson/buyAdd", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> addBuyInfo(@RequestBody BuyInfo buyInfo){
		
		Map<String, String> result = new HashMap<>();
		
		LoginMember mInfo = cf.loginInfo(req);
		if(cf.chkNull(mInfo.getMember_id())) {
			result.put("code", "-1");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			return  result;
		}
		
		Product product = serviceProd.getProductById(buyInfo.getProd_idx());
		
		if(product == null) {
			result.put("code", "-6");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			return  result;
		}
		
		buyInfo.setSell_id(product.getMember_id());
		buyInfo.setBuy_id(mInfo.getMember_id());
		
		if(service.addBuyInfo(buyInfo) > 0) {
			result.put("code", "1");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
		}else {
			result.put("code", "-7");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
		}
		
		return result;
		
	}
	
	/**
	 * �Ǹ����� ��ǰ ����Ʈ(ȸ�� ����)
	 * @param searchInfo
	 * @return
	 */
	@GetMapping(value="/prodJson/prodInfo", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
	public List<Product> selSelfProdInfo(){
				
		LoginMember mInfo = cf.loginInfo(req);
		
		return service.selProdIdInfo(mInfo.getMember_id());
		
	}
	
	@GetMapping(value="/prodJson/bidsProdList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product> selBidsProdList(){
		
		LoginMember mInfo = cf.loginInfo(req);
		
		return service.getProdBidsList(mInfo.getMember_id());
	}
	
	
	/**
	 * ȸ�� �Ű�
	 * http://localhost:8888/farmocean/member/memberfaulty/{�Ű��Ϸ���ID}
	 * @param faulty_mamner_id
	 * @return
	 */
	@GetMapping(value = "/member/memberfaulty/{faulty_mamner_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> setMemberFaulty(@PathVariable String faulty_mamner_id){
		
		LoginMember mInfo = cf.loginInfo(req);
		
		Map<String, String> result = new HashMap<>();
		
		if(mInfo.getMember_id() == null) {
			result.put("code", "-1");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			
			return result;
		}
		
		if(service.chkMember(faulty_mamner_id) == 0) {
			result.put("code", "-6");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			
			return result;
		}
		
		if(service.chkMemberFaulty(mInfo.getMember_id(), faulty_mamner_id) > 0) {
			result.put("code", "-5");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			return result;			
		}else {
			
			MemberFaulty mF = new MemberFaulty();
			
			mF.setReport_mamner_id(mInfo.getMember_id());
			mF.setFaulty_mamner_id(faulty_mamner_id);
			mF.setFaulty_memo("�׽�Ʈ�Դϴ�.");
			
			if(service.setAddMemberFaulty(mF) > 0) {
				service.setMemberFaultyCnt(faulty_mamner_id, 1);
				result.put("code", "1");
				result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			}else {
				result.put("code", "-4");
				result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			}
			
		}
		
		return result;
	}
	
	/**
	 * ȸ�� �Ű� ���
	 * http://localhost:8888/farmocean/member/memberfaultycancel/{�Ű��Ϸ���ID}
	 * @param faulty_mamner_id
	 * @return
	 */
	@GetMapping(value = "/member/memberfaultycancel/{faulty_mamner_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> setMemberFaultyCancel(@PathVariable String faulty_mamner_id){
		
		LoginMember mInfo = cf.loginInfo(req);
		
		Map<String, String> result = new HashMap<>();
		
		if(mInfo.getMember_id() == null) {
			result.put("code", "-1");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			
			return result;
		}
		
		if(service.chkMember(faulty_mamner_id) == 0) {
			result.put("code", "-6");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			
			return result;
		}
		
		if(service.chkMemberFaulty(mInfo.getMember_id(), faulty_mamner_id) > 0) {
			
			if(service.setCancelMemberFaulty(mInfo.getMember_id(), faulty_mamner_id) > 0) {
				service.setMemberFaultyCnt(faulty_mamner_id, -1);
				result.put("code", "1");
				result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			}else {
				result.put("code", "-4");
				result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			}
				
		}else {
			
			result.put("code", "-6");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			return result;		
			
		}
		
		return result;
	}
	
	@PostMapping(value = "/admin/memberInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Member selMemerInfo(@RequestBody Map<String, String> searchInfo) {
		
		Member member = new Member();

		if(cf.chkNull(searchInfo.get("type")) || cf.chkNull(searchInfo.get("value"))) {
			return member;
		}
		if(searchInfo.get("type").length() != 1) {
			searchInfo.put("type", "I");
		}
		
		switch (searchInfo.get("type")) {
			case "I":				
				member = service.selMemberIdInfo(searchInfo.get("value"));
				break;
			case "N":
				member = service.selMemberNickInfo(searchInfo.get("value"));
				break;
			default:
				break;
		}
		
		if(member == null) {
			member = new Member();
		}
		
		return member;
	}
	
	/**
	 * ������ ������ ��ǰ���� ��ȸ
	 * @param searchInfo
	 * @return
	 */
	@PostMapping(value="/admin/prodInfo", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
	public List<Product> selProdInfo(@RequestBody Map<String, String> searchInfo){
				
		List<Product> prodList;
		
		if(cf.chkNull(searchInfo.get("type")) || cf.chkNull(searchInfo.get("value"))) {
			prodList = new ArrayList<>();
			return prodList;
		}
		
		if(searchInfo.get("type").length() != 1) {
			searchInfo.put("type", "M");
		}
		
		switch (searchInfo.get("type")) {
			case "M":
				prodList = service.selProdIdInfo(searchInfo.get("value"));
				break;
			case "N":
				prodList = service.selProdNumInfo(Integer.parseInt(searchInfo.get("value")));
				break;
			case "P":
				prodList = service.selProdNameInfo(searchInfo.get("value"));
				break;
			default:
				prodList = service.selProdIdInfo(searchInfo.get("value"));
				break;
		}
		
		return prodList;
		
	}
	
	@GetMapping(value = "/admin/memberFaultyList", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
	public List<MemberFaultyInfo> selMemberFaultyList(){
		return service.selFaultyList();
	}
	
	@PostMapping(value = "/admin/memberBlock", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> setMemberStatus(@RequestBody Map<String, String> bInfo){
		
//		log.info("type : " + bInfo.get("type"));
//		log.info("userid : " + bInfo.get("userid"));
		
		Map<String, String> result = new HashMap<>();
		
		int status = 1;
		
		switch(bInfo.get("type").toUpperCase()) {
			case "B":
				status = 2;
				break;
			case "C":
				status = 1;
				break;
			default :
				status = 1;
				break;			
		}
		
		if(service.uptMemberStatus(bInfo.get("userid"), status)> 0) {
			result.put("code", "1");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
		}else {
			result.put("code", "-7");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));			
		}
			
		return result;
	}
	
	/**
	 * ��� ���� �ҷ�����
	 * @param cate ��� ����()
	 * @return
	 */
	@GetMapping(value = "/banner/{cate}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Banner> selMainTopBanner(@PathVariable String cate){
		return service.selMainTopBanner(cate);
	}

	/**
	 * �̻��(������ ���� ���)
	 * @param userid
	 * @return
	 */
	@PostMapping(value="/admin/buyList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> selBuyList(@RequestBody String userid){
		
		Map<String, Object> result = new HashMap<>();
		
		List<BuyListInfo> sellBuyList = service.selBuyList(userid);
		
		result.put("totalCount", sellBuyList.size());
		result.put("buyList", sellBuyList);
		
		return result;		
	}
	
	@PostMapping(value = "/admin/buySetatusUpt", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> setBuyStatusUpt(@RequestBody Map<String, String> bInfo){
		
		Map<String, String> result = new HashMap<>();
		
		if(service.uptBuyInfo(Integer.parseInt(bInfo.get("idx")), Integer.parseInt(bInfo.get("status")))> 0) {
			result.put("code", "1");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
		}else {
			result.put("code", "-7");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));			
		}
		
		return result;
		
	}
}














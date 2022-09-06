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

import com.ezen.farmocean.admin.dto.MemberFaulty;
import com.ezen.farmocean.admin.dto.MemberFaultyInfo;
import com.ezen.farmocean.admin.service.JsonProdService;
import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.prod.dto.Product;

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
		
		if(cf.chkNull(member)) {
			member = new Member();
		}
		
		return member;
	}
	
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
}














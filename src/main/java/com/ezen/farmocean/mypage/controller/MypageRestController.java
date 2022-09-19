package com.ezen.farmocean.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.mypage.dto.MessageBox;
import com.ezen.farmocean.mypage.dto.ProductAndDetail;
import com.ezen.farmocean.mypage.dto.ProductAndReview;
import com.ezen.farmocean.mypage.service.MessageService;
import com.ezen.farmocean.mypage.service.MypageFunction;
import com.ezen.farmocean.prod.dto.Product;
import com.ezen.farmocean.prod.dto.ProductComment;
import com.ezen.farmocean.prod.service.ProdCommentService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/memberUpdate")
public class MypageRestController {
	
	MessageService service;
	
	@Autowired
	ProdCommentService service1;
	
	@Autowired
	public MypageRestController(MessageService service) {
		this.service = service;
	}
	
	// �ɹ� ����Ʈ
	@GetMapping(value = "listAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Member> memberListAll() {
		return service.getAllMember();
	}
	
	// �г��� ���� üũ
	@GetMapping(value = "/checkNickname/{nick}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer nickCheck(@PathVariable String nick) {
		
		if (MypageFunction.checkNickName(nick) == true) {
			return 1;
		} else {			
			return 2;
		}
	}
	
	// ��й�ȣ ���� üũ
	@GetMapping(value = "/checkPassword/{pass}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer passCheck(@PathVariable String pass) {
		if (MypageFunction.checkPassword(pass) == true) {
			return 1;
		}else {			
			return 2;
		}
	}
	
	// �̸��� ���� üũ
	@GetMapping(value = "/checkEmail/{email}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer emailCheck(@PathVariable String email) {
		if (MypageFunction.checkEmail(email) == true) {
			return 1;
		}else {			
			return 2;
		}
	}
	
	// �ڵ��� ��ȣ ���� üũ
	@GetMapping(value = "/checkPhone/{phone}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer phoneCheck(@PathVariable String phone) {
		if (MypageFunction.checkPhone(phone) == true) {
			return 1;
		}else {			
			return 2;
		}
	}
	
	// ���ڸ� �Է� ����
	@GetMapping(value = "/checkNumber/{num}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer numberCheck(@PathVariable String num) {
		if (MypageFunction.checkNumber(num) == true) {
			return 1;
		}else {			
			return 2;
		}
	}
	
	// ���� ���� ���� ������ produces="application/json; charset=utf-8;"
	@GetMapping(value = "/myMessageList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MessageBox> myMessageList(String myID) {
		
		log.info("myID: "+ myID);
		
		List<Member> members = service.getMember(myID);
		
		String nickName = members.get(0).getMember_nickName();
		
		log.info("nickName: "+ nickName); 
		
		return service.getMyList(nickName);
	}
	

	
	// ���� ���� ���� ������
	@GetMapping(value = "/sendMessageList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MessageBox> sendMessageList(String myID) {
		
		log.info("myID: "+myID);
		
		List<Member> members = service.getMember(myID);
		
		String nickName = members.get(0).getMember_nickName();
		
		log.info("nickName: "+ nickName);
		
		return service.getMySendList(nickName);
	}
	
	// ���� ���� ��� 
	@GetMapping(value = "/myCommentList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ProductAndDetail> myCommentList(String myID) {
		
		log.info("myID1: "+ myID);
		
		//List<ProductAndDetail> list = service.getAllProduct(myID);
		
		return service.getAllProduct(myID);
	}
	
	// ��ǰ idx�� �̿��� ��ǰ ã��
	@GetMapping(value = "/productInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String productInfo(String myID) {
		
		log.info("myID: "+ myID);
		
		List<Product> product = service.getProductInfo(myID);
		
		String name = product.get(0).getProd_name();
		
		log.info("myID: "+ name);
		
		return name;
	}
	
	// ���� ���� �ı� 
	@GetMapping(value = "/myReviewList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ProductAndReview> myReviewList(String myID) {
		
		log.info("myID1: "+ myID);
		
		//List<ProductAndReview> list = service.getProductReview(myID);
		
		return service.getProductReview(myID);
	}
	
}

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
	
	// 맴버 리스트
	@GetMapping(value = "listAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Member> memberListAll() {
		return service.getAllMember();
	}
	
	// 닉네임 형식 체크
	@GetMapping(value = "/checkNickname/{nick}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer nickCheck(@PathVariable String nick) {
		
		if (MypageFunction.checkNickName(nick) == true) {
			return 1;
		} else {			
			return 2;
		}
	}
	
	// 비밀번호 형식 체크
	@GetMapping(value = "/checkPassword/{pass}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer passCheck(@PathVariable String pass) {
		if (MypageFunction.checkPassword(pass) == true) {
			return 1;
		}else {			
			return 2;
		}
	}
	
	// 이메일 형식 체크
	@GetMapping(value = "/checkEmail/{email}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer emailCheck(@PathVariable String email) {
		if (MypageFunction.checkEmail(email) == true) {
			return 1;
		}else {			
			return 2;
		}
	}
	
	// 핸드폰 번호 형식 체크
	@GetMapping(value = "/checkPhone/{phone}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer phoneCheck(@PathVariable String phone) {
		if (MypageFunction.checkPhone(phone) == true) {
			return 1;
		}else {			
			return 2;
		}
	}
	
	// 숫자만 입력 가능
	@GetMapping(value = "/checkNumber/{num}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer numberCheck(@PathVariable String num) {
		if (MypageFunction.checkNumber(num) == true) {
			return 1;
		}else {			
			return 2;
		}
	}
	
	// 내가 받은 쪽지 데이터 produces="application/json; charset=utf-8;"
	@GetMapping(value = "/myMessageList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MessageBox> myMessageList(String myID) {
		
		log.info("myID: "+ myID);
		
		List<Member> members = service.getMember(myID);
		
		String nickName = members.get(0).getMember_nickName();
		
		log.info("nickName: "+ nickName); 
		
		return service.getMyList(nickName);
	}
	

	
	// 내가 보낸 쪽지 데이터
	@GetMapping(value = "/sendMessageList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MessageBox> sendMessageList(String myID) {
		
		log.info("myID: "+myID);
		
		List<Member> members = service.getMember(myID);
		
		String nickName = members.get(0).getMember_nickName();
		
		log.info("nickName: "+ nickName);
		
		return service.getMySendList(nickName);
	}
	
	// 내가 남긴 댓글 
	@GetMapping(value = "/myCommentList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ProductAndDetail> myCommentList(String myID) {
		
		log.info("myID1: "+ myID);
		
		//List<ProductAndDetail> list = service.getAllProduct(myID);
		
		return service.getAllProduct(myID);
	}
	
	// 상품 idx를 이용해 상품 찾기
	@GetMapping(value = "/productInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String productInfo(String myID) {
		
		log.info("myID: "+ myID);
		
		List<Product> product = service.getProductInfo(myID);
		
		String name = product.get(0).getProd_name();
		
		log.info("myID: "+ name);
		
		return name;
	}
	
	// 내가 남긴 후기 
	@GetMapping(value = "/myReviewList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ProductAndReview> myReviewList(String myID) {
		
		log.info("myID1: "+ myID);
		
		//List<ProductAndReview> list = service.getProductReview(myID);
		
		return service.getProductReview(myID);
	}
	
}

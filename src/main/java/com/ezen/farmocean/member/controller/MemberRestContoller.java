package com.ezen.farmocean.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.member.dto.BuyMember;
import com.ezen.farmocean.member.dto.SellMember;
import com.ezen.farmocean.member.service.MemberService;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/member")
@RestController
public class MemberRestContoller {

	@Autowired
	MemberService service;

	
	 
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<BuyMember> listBuyer() {

		return service.getList();
	}
	
	@GetMapping(value = "/sellerlist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<SellMember> listSeller() {

		return service.getSellerList();
	}
	
	
	
	
		@GetMapping(value= "/list/{buy_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public BuyMember loginedMember (@PathVariable String buy_id) {
			
			return service.getMember(buy_id);
		}
		
		@GetMapping(value = "/sellerlist/{sell_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public SellMember getSeller(@PathVariable String sell_id) {

			return service.getSeller(sell_id);
		}
	
	
	// 회원가입
	@PostMapping(value = "/insert/buyer", produces = MediaType.TEXT_PLAIN_VALUE)
	public  ResponseEntity<BuyMember> insertUser(@RequestBody BuyMember buyer) {

		if(buyer.getBuy_id() == null || buyer.getBuy_id().trim().equals("") || 
				buyer.getBuy_name() == null||
						buyer.getBuy_email() == null ) {
			return ResponseEntity.badRequest().build();
		}
		
		try {
			service.insert(buyer);
			
			return ResponseEntity.ok().build();
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
		
	}
	
	@PostMapping(value = "/insert/seller", produces = MediaType.TEXT_PLAIN_VALUE)
	public  ResponseEntity<SellMember> insertSeller(@RequestBody SellMember seller) {

		if(seller.getSell_id() == null || seller.getSell_id().trim().equals("") || 
				seller.getSell_name() == null||
						seller.getSell_email() == null ) {
			return ResponseEntity.badRequest().build();
		}
		
		try {
			service.insertSeller(seller);
			
			return ResponseEntity.ok().build();
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
	
	@GetMapping(value= "/dologin/{buy_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void doLogin(
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable String buy_id) {
		HttpSession session = request.getSession();
		session.setAttribute("logined", buy_id);
		
		log.info("로그인아이디는 : "+session.getAttribute("logined"));
	}
	
	@GetMapping(value= "/sellerlogin/{sell_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void sellerLogin(
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable String sell_id) {
		HttpSession session = request.getSession();
		session.setAttribute("logined", sell_id);
		session.setAttribute("seller", "sellMember");
		
		log.info("로그인아이디는 : "+session.getAttribute("logined"));
		log.info("판매자 등급은 : "+session.getAttribute("seller"));
	}
	
}

package com.ezen.farmocean.member.controller;

import java.util.List;

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

import com.ezen.farmocean.member.dto.SellMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.service.MemberService;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/member")
@RestController
public class MemberRestContoller {

	@Autowired
	MemberService service;

	
	 
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Member> listBuyer() {

		return service.getList();
	}
	
	@GetMapping(value = "/sellerlist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<SellMember> listSeller() {

		return service.getSellerList();
	}
	
	
	
	
		@GetMapping(value= "/list/{member_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Member loginedMember (@PathVariable String member_id) {
			
			return service.getMember(member_id);
		}
		
//		@GetMapping(value = "/sellerlist/{sell_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//		public SellMember getSeller(@PathVariable String sell_id) {
//
//			return service.getSeller(sell_id);
//		}
	
	
	// 회원가입
	@PostMapping(value = "/insert/member", produces = MediaType.TEXT_PLAIN_VALUE)
	public  ResponseEntity<Member> insertUser(@RequestBody Member member) {

		if(member.getMember_id() == null || member.getMember_id().trim().equals("") || 
				member.getMember_name() == null||
						member.getMember_email() == null ) {
			return ResponseEntity.badRequest().build();
		}
		
		try {
			service.insert(member);
			
			return ResponseEntity.ok().build();
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
	
//	@PostMapping(value = "/insert/seller", produces = MediaType.TEXT_PLAIN_VALUE)
//	public  ResponseEntity<SellMember> insertSeller(@RequestBody SellMember seller) {
//
//		if(seller.getSell_id() == null || seller.getSell_id().trim().equals("") || 
//				seller.getSell_name() == null||
//						seller.getSell_email() == null ) {
//			return ResponseEntity.badRequest().build();
//		}
//		
//		try {
//			service.insertSeller(seller);
//			
//			return ResponseEntity.ok().build();
//		} catch(Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.build();
//		}
//	}
}

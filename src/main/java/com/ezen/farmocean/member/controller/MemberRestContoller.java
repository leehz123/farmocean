package com.ezen.farmocean.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.member.dto.BuyMember;
import com.ezen.farmocean.member.service.MemberService;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class MemberRestContoller {

	@Autowired
	MemberService service;

	
	// db연결 테스트 
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<BuyMember> listUser() {

		return service.getList();
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
	
}

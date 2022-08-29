package com.ezen.farmocean.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.mypage.service.MypageService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/mypage")
public class MypageRestController {
	
	MypageService service;
	
	@Autowired
	public MypageRestController(MypageService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/memberList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Member> getList(){
		
		return service.getAllMemberList();
		
	}

}

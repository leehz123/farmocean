package com.ezen.farmocean.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.mypage.service.MessageService;
import com.ezen.farmocean.mypage.service.MypageFunction;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/memberUpdate")
public class MypageRestController {
	
	MessageService service;
	
	@Autowired
	public MypageRestController(MessageService service) {
		this.service = service;
	}
	
	@GetMapping(value = "listAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Member> memberListAll() {
		return service.getAllMember();
	}
	
	@GetMapping(value = "/checkNickname/{nick}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer nickCheck(@PathVariable String nick) {
		if (MypageFunction.checkNickName(nick) == true) {
			return 1;
		}else {			
			return 2;
		}
	}
}

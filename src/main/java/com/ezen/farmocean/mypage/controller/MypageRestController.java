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
}

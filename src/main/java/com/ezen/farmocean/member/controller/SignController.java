package com.ezen.farmocean.member.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.farmocean.member.service.MemberService;

@Controller
public class SignController {
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Locale locale, Model model) {
		
		return "join";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String loginSuccess(Locale locale, Model model) {
		
		return "success";
	}
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/login")
	public String ajax(Model model) {

		model.addAttribute("buyer",memberService.getList());
		return "login";
	}
}

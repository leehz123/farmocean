package com.ezen.farmocean.member.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/member")
@Controller
public class SignController {
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Locale locale, Model model) {
		
		return "member/join";
	}
	
	@RequestMapping(value = "/sellerjoin", method = RequestMethod.GET)
	public String sellerjoin(Locale locale, Model model) {
		
		return "member/sellerJoin";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String loginSuccess(Locale locale, Model model) {
		
		return "member/success";
	}
	
	@Autowired
	MemberService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		
		return "member/login";
	}
	
	
	@RequestMapping(value="/logincheck", method=RequestMethod.POST)
    public String loginPOST(
    		Locale locale, 
    		HttpServletRequest request,
    		LoginMember member) throws Exception{
		LoginMember loginMember = service.loginCheck(member);

		 HttpSession session = request.getSession();
		 if(loginMember == null) {  

	            return "member/login";
	        }
	        session.setAttribute("loginId", loginMember);             // ��ġ�ϴ� ���̵�, ��й�ȣ ��� (�α��� ����)

	        return "member/success";
        
    }
	
	
	@RequestMapping(value = "/searchId", method = RequestMethod.GET)
	public String searchId(Locale locale, Model model) {
		
		return "member/searchId";
	}
	
	@RequestMapping(value = "/searchPw", method = RequestMethod.GET)
	public String searchPw(Locale locale, Model model) {
		
		return "member/searchPw";
	}
	
	
}

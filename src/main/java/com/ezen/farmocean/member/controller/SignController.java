package com.ezen.farmocean.member.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.farmocean.member.dto.BuyMember;
import com.ezen.farmocean.member.dto.SellMember;
import com.ezen.farmocean.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/member")
@Controller
public class SignController {
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Locale locale, Model model) {
		
		return "join";
	}
	
	@RequestMapping(value = "/join/seller", method = RequestMethod.GET)
	public String sellerjoin(Locale locale, Model model) {
		
		return "sellerJoin";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String loginSuccess(Locale locale, Model model) {
		
		return "success";
	}
	
	@Autowired
	MemberService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		
		return "login";
	}
	
	@RequestMapping(value = "/buyerlogin", method = RequestMethod.GET)
	public String buyerLogin(Locale locale, Model model) {
		
		return "login";
	}
	
	@RequestMapping(value = "/sellerlogin", method = RequestMethod.GET)
	public String sellerLogin(Locale locale, Model model) {
		
		return "sellerLogin";
	}
	
	@RequestMapping(value="/logincheck", method=RequestMethod.POST)
    public String loginPOST(
    		Locale locale, 
    		HttpServletRequest request,
    		BuyMember buyer) throws Exception{
		BuyMember buyMember = service.loginCheck(buyer);
		
		 HttpSession session = request.getSession();
		 if(buyMember == null) {  

	            return "login";
	        }
	        
		 log.info("id: "+buyMember.getBuy_id());
		 log.info("pw: "+buyMember.getBuy_name());
		 log.info("point: "+buyMember.getBuy_point());
	        session.setAttribute("loginId", buyMember);             // 일치하는 아이디, 비밀번호 경우 (로그인 성공)
	        session.setAttribute("memberType", "buyer");
	        return "success";
        
    }
	
	@RequestMapping(value="/sellerlogincheck", method=RequestMethod.POST)
    public String sellerLoginPOST(Locale locale, HttpServletRequest request, SellMember seller) throws Exception{
		SellMember sellMember = service.sellerLoginCheck(seller);
		
		 HttpSession session = request.getSession();
		 if(sellMember == null) {                                // 일치하지 않는 아이디, 비밀번호 입력 경우
	           
	           
	            return "sellerLogin";
	        }
	        
		 log.info("id: "+sellMember.getSell_id());
		 log.info("pw: "+sellMember.getSell_pw());
		 log.info("name: "+sellMember.getSell_name());
	        session.setAttribute("loginId", sellMember);             // 일치하는 아이디, 비밀번호 경우 (로그인 성공)
	        session.setAttribute("memberType", "seller");
	        
	        return "success";
        
    }
}

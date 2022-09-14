package com.ezen.farmocean.member.controller;

import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

	@RequestMapping(value = "/logincheck", method = RequestMethod.POST)
	public String loginPOST(Locale locale, HttpServletRequest request, HttpServletResponse response, LoginMember member)
			throws Exception {
//		member.pw_encrypt(member.getMember_pw());
//		member.setMember_pw(member.pw_decrypt(member.getMember_pw()));
		LoginMember loginMember = service.loginCheck(member);

		HttpSession session = request.getSession();
		if (loginMember == null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('로그인 정보를 확인해주세요.'); history.go(-1);</script>");
			out.flush();

			return "member/login";
			
		} else {
			PrintWriter out = response.getWriter();
			
			session.setAttribute("loginId", loginMember); // 일치하는 아이디, 비밀번호 경우 (로그인 성공)
			out.println("<script>window.history.forward();</script>");	
			return "member/success";
		}

	}

	@RequestMapping(value = "/naverlogincheck", method = RequestMethod.POST)
	public String naverLoginPOST(Locale locale, HttpServletRequest request, HttpServletResponse response, Member member)
			throws Exception {
		member.setMember_pw(member.pw_encrypt(member.getMember_pw()));
		log.info(member.pw_encrypt(member.getMember_pw()));
		log.info(member.pw_decrypt(member.getMember_pw()));
		Member loginMember = service.naverLoginCheck(member);

		HttpSession session = request.getSession();
		if (loginMember == null) {

			session.setAttribute("naverId", member);
			return "member/naver_select_type";
		} else {

			LoginMember naverLoginMember = new LoginMember();
			naverLoginMember.setMember_id(loginMember.getMember_id());
			naverLoginMember.setMember_name(loginMember.getMember_name());
			naverLoginMember.setMember_nickName(loginMember.getMember_nickName());
			naverLoginMember.setMember_pw(loginMember.getMember_pw());
			naverLoginMember.setMember_type(loginMember.getMember_type());
			session.setAttribute("loginId", naverLoginMember); // 일치하는 아이디, 비밀번호 경우 (로그인 성공)
			return "member/success";
		}
	}

	@RequestMapping(value = "/naverBuyerJoin", method = RequestMethod.GET)
	public String naverBuyerJoin(Locale locale, Model model) {

		return "member/naverBuyerJoin";
	}

	@RequestMapping(value = "/naverSellerJoin", method = RequestMethod.GET)
	public String naversellerJoin(Locale locale, Model model) {

		return "member/naverSellerJoin";
	}

	@RequestMapping(value = "/searchId", method = RequestMethod.GET)
	public String searchId(Locale locale, Model model) {

		return "member/searchId";
	}

	@RequestMapping(value = "/searchPw", method = RequestMethod.GET)
	public String searchPw(Locale locale, Model model) {

		return "member/searchPw";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, HttpServletRequest request, LoginMember member) throws Exception {

		HttpSession session = request.getSession();
		session.invalidate();

		return "member/logout";
	}

	@RequestMapping(value = "/naver_callback", method = RequestMethod.GET)
	public String naverLogin(Locale locale, Model model) {

		return "member/naver_callback";
	}

}

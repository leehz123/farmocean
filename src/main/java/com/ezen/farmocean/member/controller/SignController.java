package com.ezen.farmocean.member.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.mainPage.dto.Criteria;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.mainPage.dto.ProductView;
import com.ezen.farmocean.mainPage.service.ProductListService;
import com.ezen.farmocean.mainPage.service.ProductService;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/member")
@Controller
public class SignController {
	public static List<String> logined_member = new ArrayList<>();

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Locale locale, Model model) {

		return "member/buyerJoin";
	}

	@RequestMapping(value = "/sellerjoin", method = RequestMethod.GET)
	public String sellerjoin(Locale locale, Model model) {

		return "member/sellerJoin";
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String loginSuccess(Locale locale, Model model) {

		return "member/success";
	}

	@RequestMapping(value = "/login2", method = RequestMethod.GET)
	public String login2(Locale locale, Model model) {

		return "member/loginn";
	}

	@Autowired
	MemberService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model, String retUrl) {
		log.info(retUrl);

		model.addAttribute("retUrl", retUrl);
		return "member/login";
	}

	@Autowired
	private HttpServletRequest req;

	@Autowired
	private ProductService prodService;

	@Autowired
	private ProductListService prodListService;

	@Autowired
	private CommonFunction cf;

	@RequestMapping(value = "/logincheck", method = RequestMethod.POST)
	public String loginPOST(Locale locale, HttpServletRequest request, HttpServletResponse response, LoginMember member,
			Criteria cri, Model model, String member_id) throws Exception {
		member.setMember_pw(member.pwEncrypt(member.getMember_pw()));
		LoginMember loginMember = service.loginCheck(member);
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if (loginMember == null) {
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('로그인 정보를 확인해주세요.'); history.go(-1);</script>");
			out.flush();

			return "member/login";

		} else {

			if (!logined_member.contains(loginMember.getMember_id())) {
				loginMember.setDec();
				session.setAttribute("loginId", loginMember); // 일치하는 아이디, 비밀번호 경우 (로그인 성공)
				logined_member.add(loginMember.getMember_id());
				System.out.println("접속 회원입니다" + logined_member);

				String returnUrl = "/";

				if (!cf.chkNull(member.getRetUrl())) {
					returnUrl = member.getRetUrl();
				}

				return "redirect:" + returnUrl;
			}
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('중복접속입니다'); history.go(-1);</script>");
			out.flush();

			return "member/login";
		}

	}

	@RequestMapping(value = "/naverlogincheck", method = RequestMethod.POST)
	public String naverLoginPOST(Locale locale, HttpServletRequest request, HttpServletResponse response, Member member,
			Criteria cri, Model model, String member_id) throws Exception {
		member.setMember_pw(member.pwEncrypt(member.getMember_pw()));

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

			if (!logined_member.contains(loginMember.getMember_id())) {
				loginMember.setDec();
				session.setAttribute("loginId", loginMember); // 일치하는 아이디, 비밀번호 경우 (로그인 성공)
				logined_member.add(loginMember.getMember_id());
				System.out.println("접속 회원입니다" + logined_member);

				String returnUrl = "/";

				if (!cf.chkNull(member.getRetUrl())) {
					returnUrl = member.getRetUrl();
				}

				return "redirect:" + returnUrl;
			}
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('중복접속입니다'); history.go(-1);</script>");
			out.flush();

			return "member/login";
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
	public String logout(Locale locale, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		LoginMember member = (LoginMember) session.getAttribute("loginId");
		logined_member.remove(member.getMember_id());
		session.invalidate();
		System.out.println("로그아웃 했습니다 " + logined_member);
		return "member/logout";
	}

	@RequestMapping(value = "/naver_callback", method = RequestMethod.GET)
	public String naverLogin(Locale locale, Model model) {

		return "member/naver_callback";
	}

//	@RequestMapping(value = "/come", method = RequestMethod.GET)
//	public String pwChange() throws Exception {
//		List<Member>list = service.getList();
//		for(int i = 0 ; i < list.size(); ++i) {
//			Member a = list.get(i);
//			a.setMember_pw(a.pwEncrypt(a.getMember_pw()));
//			
//			service.pwChange(a);	
//		}
//		
////		list.get(0).setMember_pw(list.get(0).pwEncrypt(list.get(0).getMember_pw()));
////		service.pwChange(a);
//		
//		return "member/login";
//	}

	@RequestMapping(value = "/pwChange", method = RequestMethod.GET)
	public String pwChange(Locale locale, LoginMember member) throws Exception {

		return "member/changePw";
	}

}

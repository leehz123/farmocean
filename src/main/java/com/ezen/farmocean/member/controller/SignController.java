package com.ezen.farmocean.member.controller;

import java.io.PrintWriter;
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
	public String login(Locale locale, Model model) {

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
			Criteria cri, Model model) throws Exception {

		member.setMember_pw(member.encrypt(member.getMember_pw()));
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
			log.info("메인페이지 진입");

			// 찜 갯수 베스트 8 테스트
			/* 상품 리스트 데이터 */
			List list = prodListService.getProcBidsList();

			if (!list.isEmpty()) {
				model.addAttribute("list", list);
			}

			// 최신순 테스트
			List list2 = prodListService.getProcNewList();

			if (!list2.isEmpty()) {
				model.addAttribute("list2", list2);
			}

			// 인기순 테스트
			List list3 = prodListService.getProcPopList();

			if (!list3.isEmpty()) {
				model.addAttribute("list3", list3);
			}

			return "/mainpage/main";
		}

	}

	@RequestMapping(value = "/naverlogincheck", method = RequestMethod.POST)
	public String naverLoginPOST(Locale locale, HttpServletRequest request, HttpServletResponse response, Member member,
			Criteria cri, Model model) throws Exception {
		member.setMember_pw(member.pwEncrypt(member.getMember_pw()));
		log.info(member.encrypt(member.getMember_pw()));
		log.info(member.decrypt(member.getMember_pw()));
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
			log.info("메인페이지 진입");

			// 찜 갯수 베스트 8 테스트
			/* 상품 리스트 데이터 */
			List list = prodListService.getProcBidsList();

			if (!list.isEmpty()) {
				model.addAttribute("list", list);
			}

			// 최신순 테스트
			List list2 = prodListService.getProcNewList();

			if (!list2.isEmpty()) {
				model.addAttribute("list2", list2);
			}

			// 인기순 테스트
			List list3 = prodListService.getProcPopList();

			if (!list3.isEmpty()) {
				model.addAttribute("list3", list3);
			}

			return "/mainpage/main";
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

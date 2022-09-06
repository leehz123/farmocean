package com.ezen.farmocean.mainPage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class MemberController {
	
	// 비동기식 방식 로그아웃
	@RequestMapping(value="logout.do", method = RequestMethod.POST)
	@ResponseBody
	public void logoutPOST(HttpServletRequest request) throws Exception {
		
		log.info("비동기 로그아웃 매서드 진입");
		
		HttpSession session = request.getSession();
		session.invalidate();
	}

}

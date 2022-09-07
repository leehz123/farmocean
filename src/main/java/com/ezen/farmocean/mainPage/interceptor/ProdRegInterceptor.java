package com.ezen.farmocean.mainPage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class ProdRegInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle 
		(HttpServletRequest request, HttpServletResponse response, Object handler) 
				throws Exception {
		
		HttpSession session = request.getSession();
		
//		MemberDTO mdto = request.getAttribute("member"); // 머지 후 주석 풀기
		
//		if (mdto == null || mdto.getSellerCk() == 0) { // 판매자 계정이 아닌 경우인데 디비 확인하고 다시 바꾸자
//			
//			response.sendRedirect("/farmoceanTest/main"); // 메인 페이지로 이동
//			
//			return false;
//		}
		
		return true; // 판매자 계정으로 로그인 경우
	}

}

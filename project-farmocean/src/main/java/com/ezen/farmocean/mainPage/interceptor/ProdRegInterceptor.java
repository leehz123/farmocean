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
		
//		MemberDTO mdto = request.getAttribute("member"); // ���� �� �ּ� Ǯ��
		
//		if (mdto == null || mdto.getSellerCk() == 0) { // �Ǹ��� ������ �ƴ� ����ε� ��� Ȯ���ϰ� �ٽ� �ٲ���
//			
//			response.sendRedirect("/farmoceanTest/main"); // ���� �������� �̵�
//			
//			return false;
//		}
		
		return true; // �Ǹ��� �������� �α��� ���
	}

}

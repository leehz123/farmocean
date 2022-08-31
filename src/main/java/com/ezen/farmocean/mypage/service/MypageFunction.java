package com.ezen.farmocean.mypage.service;

import java.util.regex.Pattern;

public class MypageFunction {
	
	// �г��� ���� üũ
	public static boolean checkNickName(String value) {
		String pattern = "^(?=.*[a-z0-9��-�R])[a-z0-9��-�R]{2,16}$";
		
		return Pattern.matches(pattern, value);
	}
	
	// ��й�ȣ ���� üũ
	public static boolean checkPassword(String value) {		
		//��й�ȣ (����, ����, Ư������ ���� 8~15�ڸ� �̳�)	
		String pattern = "^.*(?=^.{8,15}$)(?=.*\\\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";
		String num = "^[0-9]+$";
		
		return Pattern.matches(num, value);
	}
	
	// �̸��� ���� üũ
	public static boolean checkEmail(String value) {		
		String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";		
		
		return Pattern.matches(pattern, value);
	}
	
	// �ڵ��� ��ȣ ���� üũ
	public static boolean checkPhone(String value) {		
		String pattern = "^\\d{3}-\\d{3,4}-\\d{4}$";	
		
		return Pattern.matches(pattern, value);
	}

}

package com.ezen.farmocean.mypage.service;

import java.util.regex.Pattern;

public class MypageFunction {
	
	// �г��� ���� üũ
	public static boolean checkNickName(String value) {
		String pattern = "^(?=.*[a-z0-9��-�R])[a-z0-9��-�R]{2,16}$";
		
		return Pattern.matches(pattern, value);
	}

}

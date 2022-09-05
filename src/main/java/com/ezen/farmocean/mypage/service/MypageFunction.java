package com.ezen.farmocean.mypage.service;

import java.util.regex.Pattern;

public class MypageFunction {
	
	// ´Ð³×ÀÓ Çü½Ä Ã¼Å©
	public static boolean checkNickName(String value) {
		String pattern = "^(?=.*[a-z0-9°¡-ÆR])[a-z0-9°¡-ÆR]{2,16}$";
		
		return Pattern.matches(pattern, value);
	}
	
	// ºñ¹Ð¹øÈ£ Çü½Ä Ã¼Å©
	public static boolean checkPassword(String value) {		
		//ºñ¹Ð¹øÈ£ (¼ýÀÚ, ¹®ÀÚ, Æ¯¼ö¹®ÀÚ ÃÖ¼Ò 1°³¾¿ Æ÷ÇÔ 8~15ÀÚ¸® ÀÌ³»)	
		String pattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,15}$";
		
		return Pattern.matches(pattern, value);
	}
	
	// ÀÌ¸ÞÀÏ Çü½Ä Ã¼Å©
	public static boolean checkEmail(String value) {		
		//String patterns = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";		
		String pattern = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$";	
//						  ^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$
//						  ^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$
		//String num = "^[0-9]+$";
		
		return Pattern.matches(pattern, value);
	}
	
	// ÇÚµåÆù ¹øÈ£ Çü½Ä Ã¼Å©
	public static boolean checkPhone(String value) {		
		String pattern = "^\\d{3}-\\d{3,4}-\\d{4}$";	
		
		return Pattern.matches(pattern, value);
	}

}

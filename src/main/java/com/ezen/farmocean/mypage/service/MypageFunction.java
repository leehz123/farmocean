package com.ezen.farmocean.mypage.service;

import java.util.regex.Pattern;

public class MypageFunction {
	
	// ´Ð³×ÀÓ Çü½Ä Ã¼Å©
	public static boolean checkNickName(String value) {
		String pattern = "^(?=.*[a-z0-9°¡-ÆR])[a-z0-9°¡-ÆR]{2,16}$";
		
		return Pattern.matches(pattern, value);
	}

}

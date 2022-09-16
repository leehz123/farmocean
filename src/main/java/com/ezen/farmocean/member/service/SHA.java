package com.ezen.farmocean.member.service;

import java.security.MessageDigest;
import java.util.Base64;

public class SHA {
	
	public String Encrypt(String text) {
		String encryptedText = "";
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(text.getBytes("UTF-8"));
			
			encryptedText = new String(Base64.getEncoder().encode(messageDigest.digest()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return encryptedText;
	}
}

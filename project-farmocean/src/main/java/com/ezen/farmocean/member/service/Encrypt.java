package com.ezen.farmocean.member.service;

import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.util.Base64Utils;

public class Encrypt {

    public static String alg = "AES/CBC/PKCS5Padding";
    private final String key = "abcdefghabcdefghabcdefghabcdefgh"; // 32byte
    private String iv = "0123456789abcdef"; // 16byte

    // ��ȣȭ
    public String encryptAES256(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
        
        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        
        return Base64Utils.encodeToUrlSafeString(encrypted);
    }

    // ��ȣȭ
    public String decryptAES256(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64Utils.decodeFromUrlSafeString(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
    }
    
    public String pwEncrypt(String text) {
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
    
    public static void main(String[] args) {
		System.out.println(new Encrypt().pwEncrypt("abc123"));
	}
    
}

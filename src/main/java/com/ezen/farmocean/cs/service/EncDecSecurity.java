package com.ezen.farmocean.cs.service;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncDecSecurity {
	
	public String alg = "AES/CBC/PKCS5Padding";
    private final String key = "abcdefghabcdefghabcdefghabcdefgh"; // 32byte
    private String iv = "0123456789abcdef"; // 16byte
	
    /**
     * 암호화
     * @param val
     * @return
     * @throws Exception
     */
	public String enCryption(String val) throws Exception {
		
		Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(val.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
        
	}

	/**
	 * 복호화
	 * @param val
	 * @return
	 * @throws Exception
	 */
	public String deCryption(String val) throws Exception {
		Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(val);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
	}

}

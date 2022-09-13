package com.ezen.farmocean.member.dto;

import com.ezen.farmocean.member.service.Encrypt;
import com.ezen.farmocean.member.service.SHA;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginMember {

	private String member_id;
	private String member_pw;
	private String member_name;
	private String member_nickName;
	private String member_type; // S = 판매자(Seller), B = 구매자(Buyer)
	
	public String pw_encrypt(String pw) throws Exception{
		
		return new Encrypt().encryptAES256(pw);
	}
	
	public String pw_decrypt(String pw) throws Exception{
		
		return new Encrypt().decryptAES256(pw);
	}
	
}

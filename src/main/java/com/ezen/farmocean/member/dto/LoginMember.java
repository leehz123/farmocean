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
	private String member_type; // S = �Ǹ���(Seller), B = ������(Buyer)
	
	
	
	public String encrypt(String data) throws Exception{
		
		return new Encrypt().encryptAES256(data);
	}
	
	public String decrypt(String data) throws Exception{
		
		return new Encrypt().decryptAES256(data);
	}
	
	
}

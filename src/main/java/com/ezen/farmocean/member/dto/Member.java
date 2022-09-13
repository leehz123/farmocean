package com.ezen.farmocean.member.dto;

import com.ezen.farmocean.member.service.Encrypt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {

	private String member_id;
	private String member_pw;
	private String member_name;
	private String member_nickName;
	private Integer member_point;
	private String member_email;
	private String member_phoneNum;
	private String member_accountNum;
	private String member_address;
	private String member_account_status;
	private String member_type;
	private String member_report;
	private String member_image;
	
	public String pw_encrypt(String pw) throws Exception{
		
		return new Encrypt().encryptAES256(pw);
	}
	
	public String pw_decrypt(String pw) throws Exception{
		
		return new Encrypt().decryptAES256(pw);
	}
	
}


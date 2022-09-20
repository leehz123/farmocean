package com.ezen.farmocean.member.dto;

import com.ezen.farmocean.member.service.Encrypt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member extends Encrypt{

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
	private String member_join_date;
	private String member_modify_date;
	
	public String encrypt(String data) throws Exception{
		
		return new Encrypt().encryptAES256(data);
	}
	
	public String decrypt(String data) throws Exception{
		
		return new Encrypt().decryptAES256(data);
	}
	
	public void setEnc() {
		try {
			member_pw = pwEncrypt(member_pw);
			member_accountNum = encrypt(member_accountNum);
			member_name = encrypt(member_name);
			member_address = encrypt(member_address);
			member_email = encrypt(member_email);
			member_phoneNum = encrypt(member_phoneNum);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	public void setDec() {
		try {
			member_accountNum = decrypt(member_accountNum);
			member_name = decrypt(member_name);
			member_address = decrypt(member_address);
			member_email = decrypt(member_email);
			member_phoneNum = decrypt(member_phoneNum);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
}


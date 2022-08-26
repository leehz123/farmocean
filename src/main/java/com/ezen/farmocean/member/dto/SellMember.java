package com.ezen.farmocean.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellMember {

	private String sell_id;
	private String sell_pw;
	private String sell_name;
	private String sell_email;
	private String sell_phoneNum;
	private String sell_accountNum;
	private String sell_address;
	private String sell_image;
	
}

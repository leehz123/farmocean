package com.ezen.farmocean.member.dto;

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

}

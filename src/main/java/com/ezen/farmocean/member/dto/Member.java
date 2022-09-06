package com.ezen.farmocean.member.dto;

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
}

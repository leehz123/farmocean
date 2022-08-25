package com.ezen.farmocean.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BuyMember {

	private String buy_id;
	private String buy_pw;
	private String buy_name;
	private String buy_email;
	private String buy_image;
	private Integer buy_point;
}

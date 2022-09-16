package com.ezen.farmocean.prod.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class JoinReviewMember {

	//¸®ºä
	private Integer review_idx;
	private Integer prod_idx;
	//String member_id;
	private String review_content;
	private Timestamp review_date; //java.sql.Timestamp
	private Integer review_starNum;
	
	//¸â¹ö
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
	private String member_image;	
	
}

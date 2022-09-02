package com.ezen.farmocean.prod.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class JoinReviewMember {

	//¸®ºä
	Integer review_idx;
	Integer prod_idx;
	//String member_id;
	String review_content;
	Timestamp review_date; //java.sql.Timestamp
	Integer review_starNum;
	
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

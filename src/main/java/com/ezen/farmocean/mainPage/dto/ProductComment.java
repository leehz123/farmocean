package com.ezen.farmocean.mainPage.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductComment {

	Integer comment_idx;
	Integer prod_idx;
	String member_id;
	String comment_content;
	Timestamp comment_date;//java.sql.Timestamp
	Integer comment_secret;
}



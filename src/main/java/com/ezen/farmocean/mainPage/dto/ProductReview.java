package com.ezen.farmocean.mainPage.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductReview {

	Integer review_idx;
	Integer prod_idx;
	String member_id;
	String review_content;
	Timestamp review_date; //java.sql.Timestamp
	Integer review_starNum;
}


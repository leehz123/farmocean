package com.ezen.farmocean.prod.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductReview {

	private Integer review_idx;
	private Integer prod_idx;
	private String member_id;
	private String review_content;
	private Timestamp review_date; //java.sql.Timestamp
	private Integer review_starNum;
	private Long buy_idx;
	
}


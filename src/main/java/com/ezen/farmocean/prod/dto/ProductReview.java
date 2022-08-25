package com.ezen.farmocean.prod.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductReview {

	Integer review_idx;
	String review_writer;
	Integer prod_idx;
	String review_title;
	String review_content;
	Timestamp review_date; //java.sql.Timestamp
	
}


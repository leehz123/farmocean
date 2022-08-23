package com.ezen.farmocean.prod.dto;

import java.sql.Date;

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
	Date review_date; //java.sql.Date
	
}


package com.ezen.farmocean.prod.dto;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductComment {

	Integer comment_idx;
	String comment_writer;
	Integer prod_idx;
	String comment_content;
	Date comment_date;//java.sql.Date
	
}

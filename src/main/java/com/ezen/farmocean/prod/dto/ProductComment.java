package com.ezen.farmocean.prod.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class ProductComment {
	Integer comment_idx;
	Integer prod_idx;
	String member_id;
	String comment_content;
	Timestamp comment_date;//java.sql.Timestamp
	Integer comment_secret;
	//본인여부 트폴 넣기
	Integer comment_accessible;
	String comment_reply;
}



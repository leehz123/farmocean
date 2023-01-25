package com.ezen.farmocean.prod.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class ProductComment {
	private Integer comment_idx;
	private Integer prod_idx;
	private String member_id;
	private String comment_content;
	private Timestamp comment_date;//java.sql.Timestamp
	private Integer comment_secret;
	//본인여부 트폴 넣기
	private Integer comment_accessible;
	private String comment_reply;
}



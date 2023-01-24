package com.ezen.farmocean.mypage.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductAndDetail {
	
	Integer prod_idx;
	String member_id; 
	String prod_name;	
	Integer cate_idx;
	String prod_info;
	Integer prod_price;
	String prod_sell;
	Timestamp prod_sell_deadline;//java.sql.Timestamp
	Integer prod_heartnum;
	Integer prod_delete;
	Integer prod_stock;
	Timestamp prod_written_date;
	Integer comment_idx;
	String comment_content;
	Timestamp comment_date;//java.sql.Timestamp
	Integer comment_secret;
	//본인여부 트폴 넣기
	Integer comment_accessible;
	String comment_reply;

}

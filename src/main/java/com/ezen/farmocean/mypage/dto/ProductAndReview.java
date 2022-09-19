package com.ezen.farmocean.mypage.dto;

import java.sql.Timestamp;

import com.ezen.farmocean.prod.dto.JoinReviewMember;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductAndReview {
	
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
	Integer review_idx;
	String review_content;
	Timestamp review_date; //java.sql.Timestamp
	Integer review_starNum;

}

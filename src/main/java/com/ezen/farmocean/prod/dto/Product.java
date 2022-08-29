package com.ezen.farmocean.prod.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class Product {	
	
	Integer prod_idx;
	String member_id; 
	String prod_name;	
	Integer cate_idx;
	String prod_info;
	String prod_price;
	String prod_sell;
	Timestamp prod_sell_deadline;//java.sql.Timestamp

}


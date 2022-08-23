package com.ezen.farmocean.prod.dto;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class Product {
	
	Integer prod_idx;
	String sell_id; 
	String prod_name;
	String prod_cate;
	String prod_img;
	String prod_info;
	Integer prod_price;
	String prod_from;
	String prod_sell;
	Date prod_sell_deadline;//java.sql.Date
	
}


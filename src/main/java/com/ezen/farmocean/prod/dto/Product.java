package com.ezen.farmocean.prod.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class Product {	
	
	private Integer prod_idx;
	private String member_id; 
	private String prod_name;	
	private Integer cate_idx;
	private String prod_info;
	private Integer prod_price;
	private String prod_sell;
	private Timestamp prod_sell_deadline;//java.sql.Timestamp
	private Integer prod_heartnum;
	private Integer prod_delete;
	private Integer prod_stock;
	private Timestamp prod_written_date;
}


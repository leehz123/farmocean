package com.ezen.farmocean.cs.dto;



import java.sql.Date;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@lombok.Data
public class BoardCate {
	private Integer cate_idx;    
	private String cate_name; 
	private String cate_writer; 
	private Date cate_in_date;         
	private Boolean cate_use; 
}

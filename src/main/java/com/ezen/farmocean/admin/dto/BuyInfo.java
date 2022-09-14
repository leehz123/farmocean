package com.ezen.farmocean.admin.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BuyInfo {
	
	private Long buy_idx;    
	private Integer prod_idx;    
	private String sell_id; 
	private String buy_id; 
	private String post_code;  
	private String road_address; 
	private String jibun_address; 
	private String detail_address; 
	private String extraa_ddress; 
	private Integer state;     // (0 : 신청, 1:접수, 2:배송중, 3:배송확인, 4:반품, 5:취소, 10:판매완료)
	private Date reg_date;          
	private Date upt_date;          
	private String upt_id; 

}

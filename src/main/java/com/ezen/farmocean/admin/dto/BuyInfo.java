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
	private Integer state;     // (0 : ��û, 1:����, 2:�����, 3:���Ȯ��, 4:��ǰ, 5:���, 10:�ǸſϷ�)
	private Date reg_date;          
	private Date upt_date;          
	private String upt_id; 

}

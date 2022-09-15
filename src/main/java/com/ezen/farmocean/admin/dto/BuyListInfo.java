package com.ezen.farmocean.admin.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BuyListInfo {
	
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
	private String	prod_name;
	private Integer prod_price;
	private String	member_nickname;
	
	private String view_regdate;
	private String view_price;
	private String view_address;
	
	public void setAddress() {
		
		view_address = road_address.length() < 1 ?  jibun_address : road_address;
		view_address = detail_address.length() > 0 ? view_address + " " +  detail_address : view_address;
		view_address = extraa_ddress.length() > 0 ? view_address + " " +  extraa_ddress : view_address;
		
	}

}

package com.ezen.farmocean.admin.dto;

import java.sql.Date;

import com.ezen.farmocean.cs.service.EncDecSecurity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BuyListInfo extends EncDecSecurity {
	
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
	private String	prod_name;
	private Integer prod_price;
	private String	member_nickname;
	
	private String view_regdate;
	private String view_price;
	private String view_address;
	
	public void setEnc() {
		try {
			post_code = enCryption(post_code);
			road_address = enCryption(road_address);
			jibun_address = enCryption(jibun_address);
			detail_address = enCryption(detail_address);
			extraa_ddress = enCryption(extraa_ddress);			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	public void setDec() {
		try {
			post_code = deCryption(post_code);
			road_address = deCryption(road_address);
			jibun_address = deCryption(jibun_address);
			detail_address = deCryption(detail_address);
			extraa_ddress = deCryption(extraa_ddress);			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	public void setAddress() {
		
		view_address = road_address.length() < 1 ?  jibun_address : road_address;
		view_address = detail_address.length() > 0 ? view_address + " " +  detail_address : view_address;
		view_address = extraa_ddress.length() > 0 ? view_address + " " +  extraa_ddress : view_address;
		
	}

}

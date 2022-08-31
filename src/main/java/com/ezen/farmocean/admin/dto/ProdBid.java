package com.ezen.farmocean.admin.dto;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProdBid {
	
	private Integer bids_idx;   
	private Integer bids_prod_idx;   
	private String bids_member_id; 
	private Date bids_in_date;   

}

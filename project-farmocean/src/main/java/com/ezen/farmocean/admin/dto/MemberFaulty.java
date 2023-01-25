package com.ezen.farmocean.admin.dto;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MemberFaulty {
	
	private Integer faulty_idx;    
	private String report_mamner_id;  
	private String faulty_mamner_id;  
	private String faulty_memo; 
	private Date faulty_in_date;     

}

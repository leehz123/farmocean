package com.ezen.farmocean.admin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MemberFaultyInfo {

	private String member_id;
	private String member_name;
	private String member_nickName;	
	private String member_account_status;
	private Integer member_report;
}

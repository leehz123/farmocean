package com.ezen.farmocean.mainPage.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProuctImg {
	
	// ���
	private String uploadPath;
	
	// uuid
	private String uuid;
	
	// ���� �̸�
	private String fileName;
	
	// ��ǰ id
	private int prod_idx;

}

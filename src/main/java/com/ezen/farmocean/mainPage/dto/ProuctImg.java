package com.ezen.farmocean.mainPage.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProuctImg {
	
	// 경로
	private String uploadPath;
	
	// uuid
	private String uuid;
	
	// 파일 이름
	private String fileName;
	
	// 상품 id
	private int prod_idx;

}

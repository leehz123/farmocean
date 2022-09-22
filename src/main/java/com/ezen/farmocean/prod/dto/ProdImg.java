package com.ezen.farmocean.prod.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProdImg {
	
	private Integer img_idx;
	private Integer prod_idx;
	private String img_url;
	private Integer main_img; // 
	
}

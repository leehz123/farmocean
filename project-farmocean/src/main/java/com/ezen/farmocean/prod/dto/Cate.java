package com.ezen.farmocean.prod.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Cate {

	private Integer cate_idx;
	private String cate_name;
	private Integer cate_main; 
	private Integer cate_use;
}

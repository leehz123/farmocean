package com.ezen.farmocean.prod.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReviewPicture {
	
	private Integer review_picture_idx;
	private Integer review_idx;
	private String review_picture_url;
	
}

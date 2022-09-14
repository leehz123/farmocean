package com.ezen.farmocean.prod.service;

import java.util.List;

import com.ezen.farmocean.prod.dto.ReviewPicture;

public interface ReviewPictureService {

	public List<ReviewPicture> getReviewPicturebyReviewIdx(Integer review_idx);
	
	public Integer insertReviewPicture(Integer review_idx, String review_picture_url);
	
}

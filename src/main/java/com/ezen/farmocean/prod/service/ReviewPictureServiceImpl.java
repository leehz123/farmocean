package com.ezen.farmocean.prod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.prod.mapper.ReviewPictureMapper;

@Service
public class ReviewPictureServiceImpl implements ReviewPictureService {

	@Autowired
	ReviewPictureMapper rpMapper;
	
	@Override
	public Integer insertReviewPicture(Integer review_idx, String review_picture_url) {
		return rpMapper.insertReviewPicture(review_idx, review_picture_url);
	}

	
	
}

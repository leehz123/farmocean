package com.ezen.farmocean.prod.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.prod.dto.ReviewPicture;
import com.ezen.farmocean.prod.mapper.ReviewPictureMapper;

@Service
public class ReviewPictureServiceImpl implements ReviewPictureService {

	@Autowired
	ReviewPictureMapper rpMapper;
	
	@Override
	public Integer insertReviewPicture(Integer review_idx, String review_picture_url) {
		return rpMapper.insertReviewPicture(review_idx, review_picture_url);
	}

	@Override
	public List<ReviewPicture> getReviewPicturebyReviewIdx(Integer review_idx) {
		return rpMapper.getReviewPictureByReviewIdx(review_idx);
	}

	@Override
	public Integer deleteReviewPicture(Integer review_idx) {
		return rpMapper.deleteReviewPicture(review_idx);
	}

	
	
}

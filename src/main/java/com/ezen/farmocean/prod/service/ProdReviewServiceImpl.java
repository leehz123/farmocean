package com.ezen.farmocean.prod.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.prod.dto.ProductReview;
import com.ezen.farmocean.prod.mapper.ProdReviewMapper;

@Service
public class ProdReviewServiceImpl implements ProdReviewService {

	@Autowired
	ProdReviewMapper reviewMapper;
	
	
	@Override
	public List<ProductReview> getProdReviewList() {
		return reviewMapper.getProdReviewList();
	}

	@Override
	public List<ProductReview> getReviewsByMemberId(String member_id) {
		return reviewMapper.getReviewsByMemberId(member_id);
	}

	@Override
	public List<ProductReview> getReviewsByStarNum(Integer review_starnum) {
		return reviewMapper.getReviewsByStarNum(review_starnum);
	}

	@Override
	public Integer insertReview(Integer prod_idx, String member_id, String review_content, Integer review_starnum) {
		return reviewMapper.insertReview(prod_idx, member_id, review_content, review_starnum);
	}

	@Override
	public Integer updateReviewByReviewIdx(Integer review_idx, String review_content, Timestamp review_date,
			Integer review_starnum) {
		return reviewMapper.updateReviewByReviewIdx(review_idx, review_content, review_date, review_starnum);
	}

	@Override
	public Integer deleteReviewByReviewIdx(Integer review_idx) {
		return reviewMapper.deleteReviewByReviewIdx(review_idx);
	}

}

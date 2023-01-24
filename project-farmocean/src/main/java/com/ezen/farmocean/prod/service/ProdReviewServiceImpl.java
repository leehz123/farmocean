package com.ezen.farmocean.prod.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.prod.dto.ProductReview;
import com.ezen.farmocean.prod.mapper.ProdReviewMapper;
import com.ezen.farmocean.prod.mapper.ReviewPictureMapper;

@Service
public class ProdReviewServiceImpl implements ProdReviewService {

	@Autowired
	ProdReviewMapper reviewMapper;
	
	@Autowired
	ReviewPictureMapper rpMapper;
	
	@Override
	public List<ProductReview> getProdReviewList() {
		return reviewMapper.getProdReviewList();
	}

	@Override
	public List<ProductReview> getProdReviewListByProdIdx(Integer prod_idx) {
		return reviewMapper.getProdReviewListByProdIdx(prod_idx);
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
	public Integer getReviewIdxByIdAndDate(String member_id, Timestamp review_date) {
		return reviewMapper.getReviewIdxByIdAndDate(member_id, review_date);
	}
	
	@Override
	public Integer insertReview(Integer prod_idx, String member_id, String review_content, Integer review_starnum, Long buy_idx) {
		return reviewMapper.insertReview(prod_idx, member_id, review_content, review_starnum, buy_idx);
	}

	@Override
	public Integer insertReviewWithJavaTS(Integer prod_idx, String member_id, String review_content,
			Timestamp review_date, Integer review_starnum, Long buy_idx) {
		return reviewMapper.insertReviewWithJavaTS(prod_idx, member_id, review_content, review_date, review_starnum, buy_idx);
	}
	
	
	@Override
	public Integer updateReviewByReviewIdx(Integer review_idx, String review_content, Timestamp review_date,
			Integer review_starnum) {
		return reviewMapper.updateReviewByReviewIdx(review_idx, review_content, review_date, review_starnum);
	}

	
	/**
	 * ★★ return 값이 2 여야 삭제된 것! ★★
	 * 삭제되는 행 수가 2개(prod_review 테이블, review_picture 테이블)
	 */
	@Override
	public Integer deleteReviewByReviewIdx(Integer review_idx) {
		Integer a = rpMapper.deleteReviewPicture(review_idx);
		Integer b = reviewMapper.deleteReviewByReviewIdx(review_idx); 
		Integer result = a + b; 
		return result;
	}

	@Override
	public ProductReview getReviewByBuyIdx(Integer buy_idx) {
		return reviewMapper.getReviewByBuyIdx(buy_idx);
	}




}

package com.ezen.farmocean.prod.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.ProductReview;

public interface ProdReviewService {

	public List<ProductReview> getProdReviewList();

	public List<ProductReview> getProdReviewListByProdIdx(Integer prod_idx);
	
	public List<ProductReview> getReviewsByMemberId(String member_id);

	public List<ProductReview> getReviewsByStarNum(Integer review_starnum);

	public Integer getReviewIdxByIdAndDate(String member_id, Timestamp review_date);
	
	public Integer insertReview(Integer prod_idx, String member_id, String review_content, Integer review_starnum);

	public Integer insertReviewWithJavaTS(Integer prod_idx, String member_id, String review_content, Timestamp review_date, Integer review_starnum);

	
	public Integer updateReviewByReviewIdx(Integer review_idx, String review_content, Timestamp review_date, Integer review_starnum);
	
	public Integer deleteReviewByReviewIdx(Integer review_idx);
	
}


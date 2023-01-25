package com.ezen.farmocean.prod.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.ReviewPicture;

public interface ReviewPictureMapper {

	public Integer insertReviewPicture(
										@Param("review_idx") Integer review_idx,
										@Param("review_picture_url") String review_picture_url						
										);

	public List<ReviewPicture> getReviewPictureByReviewIdx(@Param("review_idx") Integer review_idx);  

	public Integer deleteReviewPicture(@Param("review_idx") Integer review_idx);
}

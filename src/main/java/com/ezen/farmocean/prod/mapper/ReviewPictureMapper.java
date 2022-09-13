package com.ezen.farmocean.prod.mapper;

import org.apache.ibatis.annotations.Param;

public interface ReviewPictureMapper {

	public Integer insertReviewPicture(
										@Param("review_idx") Integer review_idx,
										@Param("review_picture_url") String review_picture_url						
										);
	//ReviewPictureMapper
	//INSERT INTO review_picture(review_picture, review_idx, review_picture_url) VALUES(review_picture_idx.nextval, #{review_idx}, #{review_picture_url})
}

package com.ezen.farmocean.prod.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.ProductReview;

public interface ProdReviewMapper {

	
	// 모든 상품 리뷰 리스트
	public List<ProductReview> getProdReviewList();

	// 특정 상품에 대한 리뷰 리스트
	public List<ProductReview> getProdReviewListByProdIdx(@Param("prod_idx") Integer prod_idx);
	
	// 특정 아이디가 작성한 상품 리뷰 리스트
	public List<ProductReview> getReviewsByMemberId(
														@Param("member_id") String member_id
													);
	
	//작성자 아이디, 작성시간으로 review_id가져오기
	public Integer getReviewIdxByIdAndDate(
											@Param("member_id") String member_id,
											@Param("review_date") Timestamp review_date
											);
	
	// 특정 아이디가 작성한 상품 리뷰 리스트
		public List<ProductReview> getReviewsByMemberIdAndProdIdx(
															@Param("member_id") String member_id,
															@Param("prod_idx") Integer prod_idx
														);
		
	
	
	// 특정 별점에 해당하는 상품 리뷰 리스트 
	public List<ProductReview> getReviewsByStarNum(
														@Param("review_starnum") Integer review_starnum
													);
	
	
	// 상품 리뷰 추가
	public Integer insertReview(
									@Param("prod_idx") Integer prod_idx, 
									@Param("member_id") String member_id,
									@Param("review_content") String review_content, 
									@Param("review_starnum") Integer review_starnum
								);
	
	
	// 상품 리뷰 추가(컨트롤러에서 타임스탬프 만들어서 넣는 경우)
		public Integer insertReviewWithJavaTS(
										@Param("prod_idx") Integer prod_idx, 
										@Param("member_id") String member_id,
										@Param("review_content") String review_content, 
										@Param("review_date") Timestamp review_date,
										@Param("review_starnum") Integer review_starnum
									);
		
	
	
	
	// 리뷰 번호로 리뷰 내용, 날짜(오늘날짜), 별점 수정
	public Integer updateReviewByReviewIdx(
											@Param("review_idx") Integer review_idx,											
											//@Param("prod_idx") Integer prod_idx, 
											//@Param("member_id") String member_id,
											@Param("review_content") String review_content, 
											@Param("review_date") Timestamp review_date,
											@Param("review_starnum") Integer review_starnum
										);
	
	
	// 리뷰 번호로 리뷰 삭제
	public Integer deleteReviewByReviewIdx(
											@Param("review_idx") Integer review_idx
										);
	
	
}

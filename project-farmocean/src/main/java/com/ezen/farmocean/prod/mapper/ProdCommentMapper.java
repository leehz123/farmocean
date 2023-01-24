package com.ezen.farmocean.prod.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.ProductComment;

public interface ProdCommentMapper {

	// 모든 댓글 리스트
	public List<ProductComment> getCommentList();
	
	// 회원아이디로 댓글 리스트 가져오기
	public List<ProductComment> getCommentListByMemberId(@Param("member_id") String member_id); 
	
	// 특정 상품페이지 내의 댓글 리스트
	public List<ProductComment> getCommentListByProdIdx(@Param("prod_idx") Integer prod_idx);

	// 특정 아이디로 작성한 댓글 리스트
	public List<ProductComment> getCommentsByMemberId();
	
	// 특정 상품페이지 내의 특정 아이디로 작성한 댓글 리스트
	public List<ProductComment> getCOmmentsByProdIdxAndMemberId();
	
	public Integer insertComment( 
									@Param("prod_idx") Integer prod_idx, 
									@Param("member_id") String member_id, 
									@Param("comment_content") String comment_content,
									@Param("comment_secret") Integer comment_secret,
									@Param("comment_accessible") Integer comment_accessible
								);
	
	//상품 판매자는 모든 댓글 볼 수 있음
	public Integer updateSellerCommentAccessible(@Param("prod_idx") Integer prod_idx);
	
	//특정 상품 댓글리스트의 member_id가 접속중인 member_id와 일치하면 comment_accessible 을 1로 지정 (아닌건 0)
	public Integer updateUserCommentAccessible(
												@Param("member_id") String member_id,
												@Param("prod_idx") Integer prod_idx
											);
	
	public Integer updateNonUserCommentAccessible(@Param("prod_idx") Integer prod_idx);
	
	
	public Integer updateCommentReply(
										@Param("comment_idx") Integer comment_idx,
										@Param("comment_reply") String comment_reply
									);
								
	
	public Integer deleteComment(@Param("comment_idx") Integer comment_idx);
}

	

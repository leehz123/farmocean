package com.ezen.farmocean.prod.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.ProductComment;

public interface ProdCommentMapper {

	// 모든 댓글 리스트
	public List<ProductComment> getCommentList();
	
	// 특정 상품페이지 내의 댓글 리스트
	public List<ProductComment> getCommentListByProdIdx();

	// 특정 아이디로 작성한 댓글 리스트
	public List<ProductComment> getCommentsByMemberId();
	
	// 특정 상품페이지 내의 특정 아이디로 작성한 댓글 리스트
	public List<ProductComment> getCOmmentsByProdIdxAndMemberId();
	
	public Integer insertComment( 
									@Param("prod_idx") Integer prod_idx, 
									@Param("member_id") String member_id, 
									@Param("comment_content") String comment_content,
									@Param("comment_secret") Integer comment_secret
								);
	
	
}

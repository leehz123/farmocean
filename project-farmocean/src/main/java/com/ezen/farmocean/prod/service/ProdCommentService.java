package com.ezen.farmocean.prod.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.ProductComment;

public interface ProdCommentService {

	public List<ProductComment> getCommentListByMemberId(String member_id);
	
	public List<ProductComment> getCommentListByProdIdx(Integer prod_idx);
	
	public Integer insertComment(Integer prod_idx, String member_id, String comment_content, Integer comment_secret, Integer comment_accessible);

	public Integer updateSellerCommentAccessible(Integer prod_idx);
	
	public Integer updateUserCommentAccessible(Integer prod_idx, String member_id);
	
	public Integer updateNonUserCommentAccessible(Integer prod_idx);

	public Integer updateCommentReply(Integer comment_idx, String comment_content);
	
	public Integer deleteComment(Integer comment_idx);
}

package com.ezen.farmocean.prod.service;

import java.util.List;

import com.ezen.farmocean.prod.dto.ProductComment;

public interface ProdCommentService {

	public List<ProductComment> getCommentListByProdIdx(Integer prod_idx);
	
	public Integer insertComment(Integer prod_idx, String member_id, String comment_content, Integer comment_secret);

	
}

package com.ezen.farmocean.prod.service;

public interface ProdCommentService {

	public Integer insertComment(Integer prod_idx, String member_id, String comment_content, Integer comment_secret);

}

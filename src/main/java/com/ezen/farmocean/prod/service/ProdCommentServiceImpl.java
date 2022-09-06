package com.ezen.farmocean.prod.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.prod.dto.ProductComment;
import com.ezen.farmocean.prod.mapper.ProdCommentMapper;

@Service
public class ProdCommentServiceImpl implements ProdCommentService{

	@Autowired
	ProdCommentMapper commentMapper;
	
	@Override
	public Integer insertComment(Integer prod_idx, String member_id, String comment_content, Integer comment_secret, Integer comment_accessible) {
		return commentMapper.insertComment(prod_idx, member_id, comment_content, comment_secret, comment_accessible);
	}

	@Override
	public List<ProductComment> getCommentListByProdIdx(Integer prod_idx) {
		return commentMapper.getCommentListByProdIdx(prod_idx);
	}

	@Override
	public Integer updateUserCommentAccessible(Integer prod_idx, String member_id) {
		return commentMapper.updateUserCommentAccessible(member_id, prod_idx);
	}

	@Override
	public Integer updateNonUserCommentAccessible(Integer prod_idx) {
		return commentMapper.updateNonUserCommentAccessible(prod_idx);
	}


	@Override
	public Integer updateCommentReply(Integer comment_idx, String comment_content) {
		return commentMapper.updateCommentReply(comment_idx, comment_content);
	}

	
	@Override
	public Integer deleteComment(Integer comment_idx) {
		return commentMapper.deleteComment(comment_idx);
	}

	
}

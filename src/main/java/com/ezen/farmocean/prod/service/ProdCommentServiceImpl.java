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
	public Integer insertComment(Integer prod_idx, String member_id, String comment_content, Integer comment_secret) {
		return commentMapper.insertComment(prod_idx, member_id, comment_content, comment_secret);
	}

	@Override
	public List<ProductComment> getCommentListByProdIdx(Integer prod_idx) {
		return commentMapper.getCommentListByProdIdx(prod_idx);
	}	
}

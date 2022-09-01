package com.ezen.farmocean.prod.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.ProductComment;

public interface ProdCommentMapper {

	// ��� ��� ����Ʈ
	public List<ProductComment> getCommentList();
	
	// Ư�� ��ǰ������ ���� ��� ����Ʈ
	public List<ProductComment> getCommentListByProdIdx();

	// Ư�� ���̵�� �ۼ��� ��� ����Ʈ
	public List<ProductComment> getCommentsByMemberId();
	
	// Ư�� ��ǰ������ ���� Ư�� ���̵�� �ۼ��� ��� ����Ʈ
	public List<ProductComment> getCOmmentsByProdIdxAndMemberId();
	
	public Integer insertComment( 
									@Param("prod_idx") Integer prod_idx, 
									@Param("member_id") String member_id, 
									@Param("comment_content") String comment_content,
									@Param("comment_secret") Integer comment_secret
								);
	
	
}

package com.ezen.farmocean.prod.mapper;

import java.util.List;

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
	
	
}

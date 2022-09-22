package com.ezen.farmocean.prod.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.ProductComment;

public interface ProdCommentMapper {

	// ��� ��� ����Ʈ
	public List<ProductComment> getCommentList();
	
	// ȸ�����̵�� ��� ����Ʈ ��������
	public List<ProductComment> getCommentListByMemberId(@Param("member_id") String member_id); 
	
	// Ư�� ��ǰ������ ���� ��� ����Ʈ
	public List<ProductComment> getCommentListByProdIdx(@Param("prod_idx") Integer prod_idx);

	// Ư�� ���̵�� �ۼ��� ��� ����Ʈ
	public List<ProductComment> getCommentsByMemberId();
	
	// Ư�� ��ǰ������ ���� Ư�� ���̵�� �ۼ��� ��� ����Ʈ
	public List<ProductComment> getCOmmentsByProdIdxAndMemberId();
	
	public Integer insertComment( 
									@Param("prod_idx") Integer prod_idx, 
									@Param("member_id") String member_id, 
									@Param("comment_content") String comment_content,
									@Param("comment_secret") Integer comment_secret,
									@Param("comment_accessible") Integer comment_accessible
								);
	
	//��ǰ �Ǹ��ڴ� ��� ��� �� �� ����
	public Integer updateSellerCommentAccessible(@Param("prod_idx") Integer prod_idx);
	
	//Ư�� ��ǰ ��۸���Ʈ�� member_id�� �������� member_id�� ��ġ�ϸ� comment_accessible �� 1�� ���� (�ƴѰ� 0)
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

	

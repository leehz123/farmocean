package com.ezen.farmocean.prod.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.ProductReview;

public interface ProdReviewMapper {

	
	// ��� ��ǰ ���� ����Ʈ
	public List<ProductReview> getProdReviewList();

	// Ư�� ��ǰ�� ���� ���� ����Ʈ
	public List<ProductReview> getProdReviewListByProdIdx(@Param("prod_idx") Integer prod_idx);
	
	// Ư�� ���̵� �ۼ��� ��ǰ ���� ����Ʈ
	public List<ProductReview> getReviewsByMemberId(
														@Param("member_id") String member_id
													);
	
	//�ۼ��� ���̵�, �ۼ��ð����� review_id��������
	public Integer getReviewIdxByIdAndDate(
											@Param("member_id") String member_id,
											@Param("review_date") Timestamp review_date
											);
	
	// Ư�� ���̵� �ۼ��� ��ǰ ���� ����Ʈ
		public List<ProductReview> getReviewsByMemberIdAndProdIdx(
															@Param("member_id") String member_id,
															@Param("prod_idx") Integer prod_idx
														);
		
	
	
	// Ư�� ������ �ش��ϴ� ��ǰ ���� ����Ʈ 
	public List<ProductReview> getReviewsByStarNum(
														@Param("review_starnum") Integer review_starnum
													);
	
	
	// ��ǰ ���� �߰�
	public Integer insertReview(
									@Param("prod_idx") Integer prod_idx, 
									@Param("member_id") String member_id,
									@Param("review_content") String review_content, 
									@Param("review_starnum") Integer review_starnum
								);
	
	
	// ��ǰ ���� �߰�(��Ʈ�ѷ����� Ÿ�ӽ����� ���� �ִ� ���)
		public Integer insertReviewWithJavaTS(
										@Param("prod_idx") Integer prod_idx, 
										@Param("member_id") String member_id,
										@Param("review_content") String review_content, 
										@Param("review_date") Timestamp review_date,
										@Param("review_starnum") Integer review_starnum
									);
		
	
	
	
	// ���� ��ȣ�� ���� ����, ��¥(���ó�¥), ���� ����
	public Integer updateReviewByReviewIdx(
											@Param("review_idx") Integer review_idx,											
											//@Param("prod_idx") Integer prod_idx, 
											//@Param("member_id") String member_id,
											@Param("review_content") String review_content, 
											@Param("review_date") Timestamp review_date,
											@Param("review_starnum") Integer review_starnum
										);
	
	
	// ���� ��ȣ�� ���� ����
	public Integer deleteReviewByReviewIdx(
											@Param("review_idx") Integer review_idx
										);
	
	
}

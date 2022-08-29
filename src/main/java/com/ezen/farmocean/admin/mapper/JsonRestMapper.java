package com.ezen.farmocean.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.Product;

public interface JsonRestMapper {
	
	// ��ǰ �ֽż�
	public List<Product> getProcNewList();
	// ��ǰ �α��
	public List<Product> getProcPopList();
	// ��ǰ ī�װ� ���
	public List<Product> getProcCateList(String cate_idx);
	// ��ǰ �� ���
	public List<Product> getProcBidsList();	

	
	// ��ǰ �� ����
	
	// �ִ� ��ǰ���� üũ	
	public Integer getProdUseChk(Integer prod_idx);
	// �� �ߴ��� üũ
	public Integer getProdBidsChk(@Param("prod_idx") Integer prod_idx, @Param("member_id") String member_id);
	// �� �ϱ�
	public Integer setProdAddBids(@Param("prod_idx") Integer prod_idx, @Param("member_id") String member_id);
	// �� ī��Ʈ ��
	public void setProdCntUpBids(Integer prod_idx);
	// �� ���
}

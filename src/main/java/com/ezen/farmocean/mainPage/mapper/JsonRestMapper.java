package com.ezen.farmocean.mainPage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.mainPage.dto.Cate;
import com.ezen.farmocean.mainPage.dto.Product;

public interface JsonRestMapper {
	
	// ��ǰ �ֽż�
	public List<Product> getProcNewList();
	// ��ǰ �α��
	public List<Product> getProcPopList();
	// ��ǰ ī�װ� ���
	public List<Product> getProcCateList(String cate_idx);
	// ��ǰ ī�װ� ��ü ���
	public List<Cate> getProcCateAllList1();
	public List<Cate> getProcCateAllList2();
	public List<Cate> getProcCateAllList3();
	public List<Cate> getProcCateAllList4();
	public List<Cate> getProcCateAllList5();
	public List<Cate> getProcCateAllList6();
	public List<Cate> getProcCateAllList7();
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

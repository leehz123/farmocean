package com.ezen.farmocean.mainPage.mapper;

import java.util.List;

import com.ezen.farmocean.mainPage.dto.Cate;
import com.ezen.farmocean.mainPage.dto.Product;
import com.ezen.farmocean.member.dto.Member;

public interface ProductListMapper {
	
	// ��ǰ �ֽż�
	public List<Product> getProcNewList();
		
	// ��ǰ �α��
	public List<Product> getProcPopList();
			
	// ��ǰ ī�װ� ��ü ���
			
	// ��ǰ �� ���
	public List<Product> getProcBidsList();
	
	// ������ ��ȸ
	// �Ǹ��� ID �˻�
	public Member selMemberIdInfo(String member_id);
	// �Ǹ��� �г��� �˻�
	public Member selMemberNickInfo(String member_nickName);
		
	// ��ǰ ��ȸ
	// �Ǹ��� ID ��ȸ
	public List<Product> selProdIdInfo(String member_id);
	// ��ǰ ��ȣ
	public List<Product> selProdNumInfo(int prod_idx);
	// ��ǰ �̸�
	public List<Product> selProdNameInfo(String prod_name);
		
	// ī�װ� ��з�
	public List<Integer> selCateTopList();
		
	// ī�װ� �Һз�
	public List<Cate> selCateSubList(Integer cate_main);

}

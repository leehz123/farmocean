package com.ezen.farmocean.mainPage.mapper;

import java.util.List;

import com.ezen.farmocean.mainPage.dto.Criteria;
import com.ezen.farmocean.mainPage.dto.NewProduct;
import com.ezen.farmocean.mainPage.dto.Product;

public interface ProdMapper {
	
	// ��ǰ ���
	public void prodEnroll(Product prod);

	// ��ǰ ���
	public List<Product> prodsGetList(Criteria cri);
	
	// ��ǰ �˻�
	public List<Product> getProdsList(Criteria cri);
	
	// ��ǰ �� ����
	public int prodsGetTotal(Criteria cri);
	
	// �Ǹ��� id ����Ʈ ��û
	public String[] getMemberIdList(String keyword);
	
	// ��ǰ �ֽż� 
	public List<NewProduct> getProdNewList();
	
	
}

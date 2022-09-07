package com.ezen.farmocean.mainPage.service;

import java.util.List;

import com.ezen.farmocean.mainPage.dto.Criteria;
import com.ezen.farmocean.mainPage.dto.Product;

public interface ProductService {

	public void prodEnroll(Product prod);
	
	// ��ǰ ����Ʈ
	public List<Product> prodsGetList(Criteria cri);
	
	// ��ǰ �˻�
	public List<Product> getProdsList(Criteria cri);
	
	// ��ǰ �� ����
	public int prodsGetTotal(Criteria cri);
	
}

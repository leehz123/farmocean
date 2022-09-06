package com.ezen.farmocean.mainPage.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.mainPage.dto.Cate;
import com.ezen.farmocean.mainPage.dto.Product;

public interface ProductListService {
	
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

}

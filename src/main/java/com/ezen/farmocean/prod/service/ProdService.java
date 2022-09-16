package com.ezen.farmocean.prod.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.Product;

public interface ProdService {

	public List<Product> getProductList();
	
	public Product getProductById(Integer prod_idx);
	
	public List<Product> getProductsByCate(Integer cate_idx);
	
	public List<Product> getProductsByMemberId(String member_id);
	
	public List<Product> getProductsByName(String prod_name);
	
	public Integer insertProduct(String member_id, String prod_name, String prod_info, Integer cate_idx, String prod_sell, Integer prod_price, Timestamp prod_sell_deadline, Integer prod_stock, Integer prod_delete, Integer prod_heartnum);
	
	public Integer updateProduct(Integer prod_idx ,String prod_name, String prod_info, Integer cate_idx, String prod_sell, Integer prod_price, Timestamp prod_sell_deadline, Integer prod_stock, Integer prod_delete);
	

	
	


	//���� �߰�, ������ �޼���� (22.09.16)__________________________________________________________________________________
	
	//��� �Ǹ����� �ǸŻ�ǰ ��ȸ (�������� 0�� �͸�)
	public List<Product> getproductListForManager(); 
	
	//(�Ǹ��� ������������) �Ǹ��ڰ� �Ǹ��ϴ� ��ǰ ��� ���� (�������� 0, 1, 2 �� �� �� ����)  
	public List<Product> getProductListForSeller(String member_id);

	
	//��ǰ �������� 0, 1, 2�� ��ȯ
	public Integer updateProductDeleteToZeroByProdIdx (Integer prod_idx); //0
	public Integer updateProductStatusHide(Integer prod_idx); //1
	public Integer updateProductStatusDelete(Integer prod_idx); //2

	
	//prod_sell_deadline ���� �������� �ٲ㼭 �Ǹ����� �����
	public Integer expireDeadline(Integer prod_idx);
	
	//�ٵ� �̰� ���� �� ��(������ �������� ���� prod_delete 1���� 0���� �ٲٷ���)
	public Integer deleteProductById(Integer prod_idx);
	
	
}

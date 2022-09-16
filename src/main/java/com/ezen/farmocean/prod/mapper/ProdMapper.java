package com.ezen.farmocean.prod.mapper;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.Product;

public interface ProdMapper {

	public List<Product> getProductList(); // �������� 0�� �͸� �� �� ����(��ü �Ǹ����� ��ǰ)
	
	public List<Product> getProductListForManager(); // �������� 0, 1, 2 �� �� �� ����(��ü �Ǹ����� ��ǰ)
	
	public Product getProductById(@Param("prod_idx") Integer prod_idx); //��ǰ idx�� ��ǰ �˻�
	
	public List<Product> getProductsByCate(@Param("cate_idx") Integer cate_idx); //ī�װ� �� ��ǰ �˻� (�������� 0�� �͸�)	
	
	public List<Product> getProductListById(@Param("member_id") String member_id); //�Ǹ��� �ƾƵ�� �ǸŻ�ǰ �˻�(�������� 0�� �͸�)
	
	public List<Product> getProductListForSeller(String member_id); // �Ǹ��� ���̵�� �ǸŻ�ǰ �˻� (�������� 0, 1, 2 �� �� �� ����) 
	
	public List<Product> getProductsByName(@Param("prod_name") String prod_name); //��ǰ �̸����� ��ǰ �˻�(�������� 0�� �͸�)

	
	//��ǰ �߰�
	public Integer insertProduct(	
									@Param("member_id") String member_id, 
									@Param("prod_name") String prod_name, 
									@Param("prod_info") String prod_info, 
									@Param("cate_idx") Integer cate_idx, 
									@Param("prod_sell") String prod_sell, //�� ���� �÷��ε� ������ �� 
									@Param("prod_price") Integer prod_price,
									@Param("prod_sell_deadline") Timestamp prod_sell_deadline,
									@Param("prod_stock") Integer prod_stock,
									@Param("prod_delete") Integer prod_delete, 
									@Param("prod_heartnum") Integer prod_heartnum									
								); 
	
	
	//�ٵ� �̰� ���� �� ��(������ �������� ���� prod_delete 1���� 0���� �ٲٷ���)
	public Integer deleteProductById(@Param("prod_idx") Integer prod_idx);
	
	public Integer updateProduct(	 
									@Param("prod_idx") Integer prod_idx,//where prod_idx = #{prod_idx}
									@Param("prod_name") String prod_name, 
									@Param("prod_info") String prod_info, 
									@Param("cate_idx") Integer cate_idx, 
									@Param("prod_sell") String prod_sell,
									@Param("prod_price") Integer  prod_price,
									@Param("prod_sell_deadline") Timestamp prod_sell_deadline,
									@Param("prod_stock") Integer prod_stock,
									@Param("prod_delete") Integer prod_delete 
								);
	
	
	//prod_delete 1���� 0���� �ٲٱ�
	public Integer updateProductDeleteToZeroByProdIdx (	 
															@Param("prod_idx") Integer prod_idx	 
														);

	////��ǰ �Ǹ� ���� (���������� ����������� ����)
	public Integer expireDeadline(
									@Param("prod_idx") Integer prod_idx
									);

	
	
	
	 

	
	public Integer updateProductStatusExist(Integer prod_idx); //���� �� �� ���·� ����
	public Integer updateProductStatusHide(Integer prod_idx);  //���� ���·� ����
	public Integer updateProductStatusDelete(Integer prod_idx); //���� ���·� ����
	 
	// �ڡڡڡڡڡڡ� deleteProductById �ִµ� �̰� ��¥ ������ ���� �ϴ� �Ŷ� ���� �� ��

	
}


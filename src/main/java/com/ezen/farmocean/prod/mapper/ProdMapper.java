package com.ezen.farmocean.prod.mapper;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.Product;

public interface ProdMapper {

	//��� �Ǹ����� ���� ���� 0 �� ��ǰ�� ��ȸ
	public List<Product> getProductList();
	
	//��ǰidx�� �ش��ϴ� ��ǰ 1 �� ��ȸ. �������� 0, 1, 2 ��� ���� (��ǰ�������� ��ȸ, ��ǰ ���̵�� ��ǰ �������� ���� ���� ������ �ٲٸ� �� ��)
	public Product getProductById(@Param("prod_idx") Integer prod_idx); 
	
	//cate_idx�� �ش��ϴ� ��ǰ ��� ��ȸ (�������� 0�� �͸�) 
	public List<Product> getProductsByCate(@Param("cate_idx") Integer cate_idx);
	
	//member_id �� �ش��ϴ� ��ǰ��� ��ȸ (�������� 0�� �͸�)
	public List<Product> getProductsByMemberId(@Param("member_id") String member_id);
	
	//��ǰ �̸��� �ش��ϴ� ��ǰ ��� ��ȸ(Like. �������� 0�� �͸� ��ȸ)
	public List<Product> getProductsByName(@Param("prod_name") String prod_name);
	
	
	//��ǰ �߰� (��ǰ�Խñ� �ۼ�) _ prod_sell �÷� ������ �ϴ� ����
	public Integer insertProduct(	
									@Param("member_id") String member_id, 
									@Param("prod_name") String prod_name, 
									@Param("prod_info") String prod_info, 
									@Param("cate_idx") Integer cate_idx, 
									@Param("prod_sell") String prod_sell, //(�� ��. prod_sell �÷� ������ ��)
									@Param("prod_price") Integer prod_price,
									@Param("prod_sell_deadline") Timestamp prod_sell_deadline,
									@Param("prod_stock") Integer prod_stock,
									@Param("prod_delete") Integer prod_delete, 
									@Param("prod_heartnum") Integer prod_heartnum									
								); 
	
	
	//��ǰ ���� (��ǰ�Խñ� ����) _ prod_sell �÷� ������ �ϴ� ����
	public Integer updateProduct(	 
									@Param("prod_idx") Integer prod_idx,//where prod_idx = #{prod_idx}
									@Param("prod_name") String prod_name, 
									@Param("prod_info") String prod_info, 
									@Param("cate_idx") Integer cate_idx, 
									@Param("prod_sell") String prod_sell, //(�� ��. prod_sell �÷� ������ ��)
									@Param("prod_price") Integer  prod_price,
									@Param("prod_sell_deadline") Timestamp prod_sell_deadline,
									@Param("prod_stock") Integer prod_stock,
									@Param("prod_delete") Integer prod_delete 
								);
	
	
	
//���� �߰�, ������ �޼���� (22.09.16)__________________________________________________________________________________
	
	//��� �Ǹ����� �ǸŻ�ǰ ��ȸ (�������� 0�� �͸�)
	public List<Product> getproductListForManager(); 
	
	//(�Ǹ��� ������������) �Ǹ��ڰ� �Ǹ��ϴ� ��ǰ ��� ���� (�������� 0, 1, 2 �� �� �� ����)  
	public List<Product> getProductListForSeller(@Param("member_id") String member_id);

	
	//��ǰ �������� 0, 1, 2�� ��ȯ
	public Integer updateProductDeleteToZeroByProdIdx (@Param("prod_idx") Integer prod_idx); //0
	public Integer updateProductStatusHide(@Param("prod_idx") Integer prod_idx); //1
	public Integer updateProductStatusDelete(@Param("prod_idx") Integer prod_idx); //2

	
	//prod_sell_deadline ���� �������� �ٲ㼭 �Ǹ����� �����
	public Integer expireDeadline(@Param("prod_idx") Integer prod_idx);

	
	//�ٵ� �̰� ���� �� ��(������ �������� ���� prod_delete 1���� 0���� �ٲٷ���)
	public Integer deleteProductById(@Param("prod_idx") Integer prod_idx);

}


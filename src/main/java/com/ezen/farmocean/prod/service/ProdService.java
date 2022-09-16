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
	

	
	


	//새로 추가, 수정된 메서드들 (22.09.16)__________________________________________________________________________________
	
	//모든 판매자의 판매상품 조회 (삭제상태 0인 것만)
	public List<Product> getproductListForManager(); 
	
	//(판메자 마이페이지용) 판매자가 판매하는 상품 목록 보기 (삭제상태 0, 1, 2 다 볼 수 있음)  
	public List<Product> getProductListForSeller(String member_id);

	
	//상품 삭제상태 0, 1, 2로 변환
	public Integer updateProductDeleteToZeroByProdIdx (Integer prod_idx); //0
	public Integer updateProductStatusHide(Integer prod_idx); //1
	public Integer updateProductStatusDelete(Integer prod_idx); //2

	
	//prod_sell_deadline 현재 시점으로 바꿔서 판매종료 만들기
	public Integer expireDeadline(Integer prod_idx);
	
	//근데 이거 쓰면 안 됨(데이터 삭제하지 말고 prod_delete 1에서 0으로 바꾸랬음)
	public Integer deleteProductById(Integer prod_idx);
	
	
}

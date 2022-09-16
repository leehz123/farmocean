package com.ezen.farmocean.prod.mapper;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.Product;

public interface ProdMapper {

	public List<Product> getProductList(); // 삭제상태 0인 것만 볼 수 있음(전체 판매자의 상품)
	
	public List<Product> getProductListForManager(); // 삭제상태 0, 1, 2 다 볼 수 있음(전체 판매자의 상품)
	
	public Product getProductById(@Param("prod_idx") Integer prod_idx); //상품 idx로 상품 검색
	
	public List<Product> getProductsByCate(@Param("cate_idx") Integer cate_idx); //카테고리 별 상품 검색 (삭제상태 0인 것만)	
	
	public List<Product> getProductListById(@Param("member_id") String member_id); //판매자 아아디로 판매상품 검색(삭제상태 0인 것만)
	
	public List<Product> getProductListForSeller(String member_id); // 판매자 아이디로 판매상품 검색 (삭제상태 0, 1, 2 다 볼 수 있음) 
	
	public List<Product> getProductsByName(@Param("prod_name") String prod_name); //상품 이름으로 상품 검색(삭제상태 0인 것만)

	
	//상품 추가
	public Integer insertProduct(	
									@Param("member_id") String member_id, 
									@Param("prod_name") String prod_name, 
									@Param("prod_info") String prod_info, 
									@Param("cate_idx") Integer cate_idx, 
									@Param("prod_sell") String prod_sell, //안 쓰는 컬럼인데 삭제는 안 
									@Param("prod_price") Integer prod_price,
									@Param("prod_sell_deadline") Timestamp prod_sell_deadline,
									@Param("prod_stock") Integer prod_stock,
									@Param("prod_delete") Integer prod_delete, 
									@Param("prod_heartnum") Integer prod_heartnum									
								); 
	
	
	//근데 이거 쓰면 안 됨(데이터 삭제하지 말고 prod_delete 1에서 0으로 바꾸랬음)
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
	
	
	//prod_delete 1에서 0으로 바꾸기
	public Integer updateProductDeleteToZeroByProdIdx (	 
															@Param("prod_idx") Integer prod_idx	 
														);

	////상품 판매 종료 (마감기한을 현재시점으로 변경)
	public Integer expireDeadline(
									@Param("prod_idx") Integer prod_idx
									);

	
	
	
	 

	
	public Integer updateProductStatusExist(Integer prod_idx); //삭제 안 된 상태로 변경
	public Integer updateProductStatusHide(Integer prod_idx);  //숨김 상태로 변경
	public Integer updateProductStatusDelete(Integer prod_idx); //삭제 상태로 변경
	 
	// ★★★★★★★ deleteProductById 있는데 이건 진짜 데이터 삭제 하는 거라 쓰면 안 됨

	
}


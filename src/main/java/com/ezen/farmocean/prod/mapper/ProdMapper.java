package com.ezen.farmocean.prod.mapper;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.prod.dto.Product;

public interface ProdMapper {

	//모든 판매자의 삭제 상태 0 인 상품만 조회
	public List<Product> getProductList();
	
	//상품idx에 해당하는 상품 1 개 조회. 삭제상태 0, 1, 2 상관 없음 (상품상세페이지 조회, 상품 아이디로 상품 가져오기 여러 군데 쓰여서 바꾸면 안 됨)
	public Product getProductById(@Param("prod_idx") Integer prod_idx); 
	
	//cate_idx에 해당하는 상품 목록 조회 (삭제상태 0인 것만) 
	public List<Product> getProductsByCate(@Param("cate_idx") Integer cate_idx);
	
	//member_id 에 해당하는 상품목록 조회 (삭제상태 0인 것만)
	public List<Product> getProductsByMemberId(@Param("member_id") String member_id);
	
	//상품 이름에 해당하는 상품 목록 조회(Like. 삭제상태 0인 것만 조회)
	public List<Product> getProductsByName(@Param("prod_name") String prod_name);
	
	
	//상품 추가 (상품게시글 작성) _ prod_sell 컬럼 삭제는 일단 보류
	public Integer insertProduct(	
									@Param("member_id") String member_id, 
									@Param("prod_name") String prod_name, 
									@Param("prod_info") String prod_info, 
									@Param("cate_idx") Integer cate_idx, 
									@Param("prod_sell") String prod_sell, //(안 씀. prod_sell 컬럼 삭제할 것)
									@Param("prod_price") Integer prod_price,
									@Param("prod_sell_deadline") Timestamp prod_sell_deadline,
									@Param("prod_stock") Integer prod_stock,
									@Param("prod_delete") Integer prod_delete, 
									@Param("prod_heartnum") Integer prod_heartnum									
								); 
	
	
	//상품 수정 (상품게시글 수정) _ prod_sell 컬럼 삭제는 일단 보류
	public Integer updateProduct(	 
									@Param("prod_idx") Integer prod_idx,//where prod_idx = #{prod_idx}
									@Param("prod_name") String prod_name, 
									@Param("prod_info") String prod_info, 
									@Param("cate_idx") Integer cate_idx, 
									@Param("prod_sell") String prod_sell, //(안 씀. prod_sell 컬럼 삭제할 것)
									@Param("prod_price") Integer  prod_price,
									@Param("prod_sell_deadline") Timestamp prod_sell_deadline,
									@Param("prod_stock") Integer prod_stock,
									@Param("prod_delete") Integer prod_delete 
								);
	
	
	
//새로 추가, 수정된 메서드들 (22.09.16)__________________________________________________________________________________
	
	//모든 판매자의 판매상품 조회 (삭제상태 0인 것만)
	public List<Product> getproductListForManager(); 
	
	//(판메자 마이페이지용) 판매자가 판매하는 상품 목록 보기 (삭제상태 0, 1, 2 다 볼 수 있음)  
	public List<Product> getProductListForSeller(@Param("member_id") String member_id);

	
	//상품 삭제상태 0, 1, 2로 변환
	public Integer updateProductDeleteToZeroByProdIdx (@Param("prod_idx") Integer prod_idx); //0
	public Integer updateProductStatusHide(@Param("prod_idx") Integer prod_idx); //1
	public Integer updateProductStatusDelete(@Param("prod_idx") Integer prod_idx); //2

	
	//prod_sell_deadline 현재 시점으로 바꿔서 판매종료 만들기
	public Integer expireDeadline(@Param("prod_idx") Integer prod_idx);

	
	//근데 이거 쓰면 안 됨(데이터 삭제하지 말고 prod_delete 1에서 0으로 바꾸랬음)
	public Integer deleteProductById(@Param("prod_idx") Integer prod_idx);

}


package com.ezen.farmocean.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.admin.dto.MemberFaulty;
import com.ezen.farmocean.admin.dto.MemberFaultyInfo;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.prod.dto.Product;

public interface AdminJsonRestMapper {
	
	// 상품 최신순
	public List<Product> getProcNewList();
	// 상품 인기순
	public List<Product> getProcPopList();
	// 상품 카테고리 목록
	public List<Product> getProcCateList(String cate_idx);
	// 상품 찜 목록
	public List<Product> getProcBidsList();	

	
	// 상품 찜 관련
	
	// 있는 상품인지 체크	
	public Integer getProdUseChk(Integer prod_idx);
	// 찜 했는지 체크
	public Integer getProdBidsChk(@Param("prod_idx") Integer prod_idx, @Param("member_id") String member_id);
	// 찜 하기
	public Integer setProdAddBids(@Param("prod_idx") Integer prod_idx, @Param("member_id") String member_id);
	// 찜 카운트 수정
	public Integer setProdCntUpBids(@Param("prod_idx") Integer prod_idx, @Param("countNum") Integer countNum);
	// 찜 취소
	public Integer setProdCancelBids(@Param("prod_idx") Integer prod_idx, @Param("member_id") String member_id);
	
	
	
	// 유저 신고 관련
	
	// 있는 유저 인지 체크
	public Integer chkMember(@Param("faulty_mamner_id") String faulty_mamner_id);
	
	// 유저 신고 내역 체크
	public Integer chkMemberFaulty(@Param("report_mamner_id") String report_mamner_id,
									@Param("faulty_mamner_id") String faulty_mamner_id);
	// 유저 신고 등록
	public Integer setAddMemberFaulty(MemberFaulty memberFaulty);
	// 유저 신고 카운팅
	public Integer setMemberFaultyCnt(@Param("faulty_mamner_id") String faulty_mamner_id, 
										@Param("countNum") Integer countNum);
	// 유저 신고 취소
	public Integer setCancelMemberFaulty(@Param("report_mamner_id") String report_mamner_id,
											@Param("faulty_mamner_id") String faulty_mamner_id);
	// 유저 신고 당한 목록
	public List<MemberFaultyInfo> selFaultyList(); 
	
	
	// 관리자 조회
	// 판매자 ID 검색
	public Member selMemberIdInfo(String member_id);
	// 판매자 닉네임 검색
	public Member selMemberNickInfo(String member_nickName);
	
	
	// 상품 조회
	// 판매자 ID 조회
	public List<Product> selProdIdInfo(String member_id);
	// 상품 번호
	public List<Product> selProdNumInfo(int prod_idx);
	// 상품 이름
	public List<Product> selProdNameInfo(String prod_name);
}


















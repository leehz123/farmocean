package com.ezen.farmocean.mypage.service;

import java.util.List;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.mypage.dto.MessageBox;
import com.ezen.farmocean.mypage.dto.ProductAndDetail;
import com.ezen.farmocean.mypage.dto.ProductAndReview;
import com.ezen.farmocean.prod.dto.Product;

public interface MessageService {
	
	// 쪽지 전체
	public List<MessageBox> getList();

	// 쪽지 내가 받은 것 만
	public List<MessageBox> getMyList(String id);
	
	// 쪽지 내가 보낸 것 만
	public List<MessageBox> getMySendList(String id);
	
	// 쪽지 보내기 (messageBox)
	public Integer getSendMessage(String myId, String id, String title, String content);
	
	// 쪽지 보내기 (SendMessageBox)
	public Integer getSendMessage2(String myId2, String id2, String title2, String content2);
	
	// 쪽지 내용 확인
	public List<MessageBox> getReadMyMessage(String id);
	
	// 쪽지 내용 확인
	public List<MessageBox> getReadMyMessage2(String id);
	
	// 쪽지 읽음 표시로 바꾸기 (messageBox)
	public int getUpdateReadMyMessage(String id);
	
	// 쪽지 읽음 표시로 바꾸기 (SendMessageBox)
	public int getUpdateReadMyMessage2(String id);
	
	// 쪽지 삭제하기 (내가 받은 쪽지)
	public Integer getDeleteMessage(String id);
	
	// 쪽지 삭제하기 (내가 보낸 쪽지)
	public Integer getDeleteSendMessage(String id);
	
	// 회원 정보 불러오기	
	public List<Member> getMember(String id);
	
	// 닉네임으로 회원 정보 불러오기
	public List<Member> getNickNameMember(String nickname);
	
	// 회원 정보 수정하기 (S)
	public Integer getUpdateinfo(Member member);
	
	// 회원 정보 수정하기 (B)
	public Integer getUpdateinfoB(Member member);
	
	// 회원 정보 모두 불러오기
	public List<Member> getAllMember();
	
	// 회원 프로필 이미지 update
	public Integer getUpdateImg(String image, String id);
	
	// 상품 판매 숨김
	public Integer getHideSellgoods(String id);
	
	// 상품 판매 보임
	public Integer getHideSellgoods2(String id);
	
	// 상품 테이블과 댓글 테이블 inner join
	public List<ProductAndDetail> getAllProduct(String id);
	
	// 상품 idx를 이용해 상품 찾기
	public List<Product> getProductInfo(String id);
	
	// 상품 테이블과 후기 테이블 inner join
	public List<ProductAndReview> getProductReview(String id);
	

}

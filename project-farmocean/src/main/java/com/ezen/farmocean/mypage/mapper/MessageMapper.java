package com.ezen.farmocean.mypage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.mypage.dto.MessageBox;
import com.ezen.farmocean.mypage.dto.ProductAndDetail;
import com.ezen.farmocean.mypage.dto.ProductAndReview;
import com.ezen.farmocean.prod.dto.Product;

public interface MessageMapper {
	
	// ���� ��ü
	public List<MessageBox> getList();
	
	// ���� ���� ���� �� ��
	public List<MessageBox> getMyList(String id);
	
	// ���� ���� ���� �� ��
	public List<MessageBox> getMySendList(String id);
	
	// ���� ������ (MessageBox)
	public Integer getSendMessage(@Param("myId") String myId, @Param("id") String id, @Param("title") String title, @Param("content") String content, @Param("realId") String realId);
	
	// ���� ������ (SendMessageBox)
	public Integer getSendMessage2(@Param("myId2") String myId2, @Param("id2") String id2, @Param("title2") String title2, @Param("content2") String content2, @Param("realId2") String realId2);
	
	// ���� ���� Ȯ��
	public List<MessageBox> getReadMyMessage(String id);
	
	// ���� ���� Ȯ��
	public List<MessageBox> getReadMyMessage2(String id);
	
	// ���� ���� ǥ�÷� �ٲٱ�
	public int getUpdateReadMyMessage(String id);
	
	// ���� ���� ǥ�÷� �ٲٱ� (SendMessageBox)
	public int getUpdateReadMyMessage2(String id);
	
	// ���� �����ϱ� (���� ���� ����)
	public Integer getDeleteMessage(String id);
		
	// ���� �����ϱ� (���� ���� ����)
	public Integer getDeleteSendMessage(String id);
	
	// ȸ�� ���� �ҷ�����
	public List<Member> getMemberList(String id);
	
	// �г������� ȸ�� ���� �ҷ�����
	public List<Member> getNickNameMember(String nickname);
	
	// ȸ�� ���� �����ϱ� (S)
	public int getUpdateinfo(Member member);
	
	// ȸ�� ���� �����ϱ� (B)
	public int getUpdateinfoB(Member member);
	
	// ȸ�� ���� ��� �ҷ�����
	public List<Member> getAllMember();

	// ȸ�� ������ �̹��� update
	public int getUpdateImg(@Param("image") String image, @Param("id") String id);
	
	// ��ǰ �Ǹ� ����
	public int getHideSellgoods(String id);
	
	// ��ǰ �Ǹ� ����
	public int getHideSellgoods2(String id);
	
	// ��ǰ ���̺�� ��� ���̺� inner join
	public List<ProductAndDetail> getAllProduct(String id);
	
	// ��ǰ idx�� �̿��� ��ǰ ã��
	public List<Product> getProductInfo(String id);
	
	// ��ǰ ���̺�� �ı� ���̺� inner join
	public List<ProductAndReview> getProductReview(String id);
}

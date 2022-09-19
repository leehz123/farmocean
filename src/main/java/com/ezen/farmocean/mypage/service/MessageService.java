package com.ezen.farmocean.mypage.service;

import java.util.List;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.mypage.dto.MessageBox;
import com.ezen.farmocean.mypage.dto.ProductAndDetail;
import com.ezen.farmocean.mypage.dto.ProductAndReview;
import com.ezen.farmocean.prod.dto.Product;

public interface MessageService {
	
	// ���� ��ü
	public List<MessageBox> getList();

	// ���� ���� ���� �� ��
	public List<MessageBox> getMyList(String id);
	
	// ���� ���� ���� �� ��
	public List<MessageBox> getMySendList(String id);
	
	// ���� ������ (messageBox)
	public Integer getSendMessage(String myId, String id, String title, String content);
	
	// ���� ������ (SendMessageBox)
	public Integer getSendMessage2(String myId2, String id2, String title2, String content2);
	
	// ���� ���� Ȯ��
	public List<MessageBox> getReadMyMessage(String id);
	
	// ���� ���� Ȯ��
	public List<MessageBox> getReadMyMessage2(String id);
	
	// ���� ���� ǥ�÷� �ٲٱ� (messageBox)
	public int getUpdateReadMyMessage(String id);
	
	// ���� ���� ǥ�÷� �ٲٱ� (SendMessageBox)
	public int getUpdateReadMyMessage2(String id);
	
	// ���� �����ϱ� (���� ���� ����)
	public Integer getDeleteMessage(String id);
	
	// ���� �����ϱ� (���� ���� ����)
	public Integer getDeleteSendMessage(String id);
	
	// ȸ�� ���� �ҷ�����	
	public List<Member> getMember(String id);
	
	// �г������� ȸ�� ���� �ҷ�����
	public List<Member> getNickNameMember(String nickname);
	
	// ȸ�� ���� �����ϱ� (S)
	public Integer getUpdateinfo(Member member);
	
	// ȸ�� ���� �����ϱ� (B)
	public Integer getUpdateinfoB(Member member);
	
	// ȸ�� ���� ��� �ҷ�����
	public List<Member> getAllMember();
	
	// ȸ�� ������ �̹��� update
	public Integer getUpdateImg(String image, String id);
	
	// ��ǰ �Ǹ� ����
	public Integer getHideSellgoods(String id);
	
	// ��ǰ �Ǹ� ����
	public Integer getHideSellgoods2(String id);
	
	// ��ǰ ���̺�� ��� ���̺� inner join
	public List<ProductAndDetail> getAllProduct(String id);
	
	// ��ǰ idx�� �̿��� ��ǰ ã��
	public List<Product> getProductInfo(String id);
	
	// ��ǰ ���̺�� �ı� ���̺� inner join
	public List<ProductAndReview> getProductReview(String id);
	

}

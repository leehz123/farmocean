package com.ezen.farmocean.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.admin.dto.Banner;
import com.ezen.farmocean.admin.dto.BuyInfo;
import com.ezen.farmocean.admin.dto.BuyListInfo;
import com.ezen.farmocean.admin.dto.MemberFaulty;
import com.ezen.farmocean.admin.dto.MemberFaultyInfo;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.prod.dto.Cate;
import com.ezen.farmocean.prod.dto.Product;

public interface AdminJsonRestMapper {
	
	// ��ǰ �ֽż�
	public List<Product> getProcNewList();
	// ��ǰ �α��
	public List<Product> getProcPopList();
	// ��ǰ ī�װ� ���
	public List<Product> getProcCateList(String cate_idx);
	// ��ǰ �� ���
	public List<Product> getProcBidsList();	

	
	// ��ǰ �� ����
	
	// �ִ� ��ǰ���� üũ	
	public Integer getProdUseChk(Integer prod_idx);
	// �� �ߴ��� üũ
	public Integer getProdBidsChk(@Param("prod_idx") Integer prod_idx, @Param("member_id") String member_id);
	// �� �ϱ�
	public Integer setProdAddBids(@Param("prod_idx") Integer prod_idx, @Param("member_id") String member_id);
	// �� ī��Ʈ ����
	public Integer setProdCntUpBids(@Param("prod_idx") Integer prod_idx, @Param("countNum") Integer countNum);
	// �� ���
	public Integer setProdCancelBids(@Param("prod_idx") Integer prod_idx, @Param("member_id") String member_id);

	/**
	 * ���� ��ǰ ���
	 * @param member_id ����ID
	 * @return ��ǰ���
	 */
	public List<Product> getProdBidsList(@Param("member_id") String member_id);
	
	
	
	
	// ���� �Ű� ����
	
	// �ִ� ���� ���� üũ
	public Integer chkMember(@Param("faulty_mamner_id") String faulty_mamner_id);
	
	// ���� �Ű� ���� üũ
	public Integer chkMemberFaulty(@Param("report_mamner_id") String report_mamner_id,
									@Param("faulty_mamner_id") String faulty_mamner_id);
	// ���� �Ű� ���
	public Integer setAddMemberFaulty(MemberFaulty memberFaulty);
	// ���� �Ű� ī����
	public Integer setMemberFaultyCnt(@Param("faulty_mamner_id") String faulty_mamner_id, 
										@Param("countNum") Integer countNum);
	// ���� �Ű� ���
	public Integer setCancelMemberFaulty(@Param("report_mamner_id") String report_mamner_id,
											@Param("faulty_mamner_id") String faulty_mamner_id);
	// ���� �Ű� ���� ���
	public List<MemberFaultyInfo> selFaultyList(); 
	
	
	// ������ ��ȸ
	// �Ǹ��� ID �˻�
	public Member selMemberIdInfo(String member_id);
	// �Ǹ��� �г��� �˻�
	public Member selMemberNickInfo(String member_nickName);
	
	
	// ��ǰ ��ȸ
	// �Ǹ��� ID ��ȸ
	public List<Product> selProdIdInfo(String member_id);
	// ��ǰ ��ȣ
	public List<Product> selProdNumInfo(int prod_idx);
	// ��ǰ �̸�
	public List<Product> selProdNameInfo(String prod_name);
	
	
	// ī�װ� ��з�
	public List<Integer> selCateTopList();
	
	// ī�װ� �Һз�
	public List<Cate> selCateSubList(Integer cate_main);
	
	// ���
	
	// ���
	public List<Banner> selMainTopBanner(String cate);
	
	// ��ʵ��
	public Integer setMainTopBanner(Banner banner);
	// ��ʼ���
	public Integer uptMainTopBanner(Banner banner);
	
	
	// ����
	// ���� ���
	public Integer addBuyInfo(BuyInfo buyInfo);
	/**
	 * ���� ��� ���� ����
	 * @param prod_idx ���� IDX
	 * @param countNum ���� (0 : ��û, 1:����, 2:�����, 3:���Ȯ��, 4:��ǰ, 5:���, 10:�ǸſϷ�)
	 * @return
	 */
	public Integer uptBuyInfo(@Param("buy_idx") Integer buy_idx, @Param("state") Integer state);
	
	/**
	 * �����ں� ���Ÿ�� ��ȸ
	 * @param member_id ������ ID
	 * @return
	 */
	public List<BuyListInfo> selBuyList(@Param("member_id") String member_id);
	
	/**
	 * �Ǹ��ں� �ǸŸ�� ��ȸ
	 * @param member_id �Ǹ��� ID
	 * @return
	 */
	public List<BuyListInfo> selSellList(@Param("member_id") String member_id);
	
	// ���
	// ���� ���
	public Integer uptMemberStatus(@Param("member_id") String member_id, @Param("status") Integer status);
}


















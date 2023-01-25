package com.ezen.farmocean.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.admin.dto.Banner;
import com.ezen.farmocean.admin.dto.BuyInfo;
import com.ezen.farmocean.admin.dto.BuyListInfo;
import com.ezen.farmocean.admin.dto.MemberFaulty;
import com.ezen.farmocean.admin.dto.MemberFaultyInfo;
import com.ezen.farmocean.admin.mapper.AdminJsonRestMapper;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.prod.dto.Cate;
import com.ezen.farmocean.prod.dto.Product;

@Service
public class JsonProdServiceImpl implements JsonProdService {
	
	@Autowired
	AdminJsonRestMapper mapper;

	@Override
	public List<Product> getProcNewList() {
		return mapper.getProcNewList();
	}

	@Override
	public List<Product> getProcPopList() {
		return mapper.getProcPopList();
	}

	@Override
	public List<Product> getProcCateList(String cate_idx) {
		return mapper.getProcCateList(cate_idx);
	}

	@Override
	public Integer getProdBidsChk(Integer prod_idx, String member_id) {
		return mapper.getProdBidsChk(prod_idx, member_id);
	}

	@Override
	public Integer getProdUseChk(Integer prod_idx) {
		return mapper.getProdUseChk(prod_idx);
	}

	@Override
	public Integer setProdAddBids(Integer prod_idx, String member_id) {
		return mapper.setProdAddBids(prod_idx, member_id);
	}	

	@Override
	public List<Product> getProcBidsList() {
		return mapper.getProcBidsList();
	}

	@Override
	public Integer setProdCntUpBids(Integer prod_idx, Integer countNum) {
		return mapper.setProdCntUpBids(prod_idx, countNum);
	}

	@Override
	public Integer setProdCancelBids(Integer prod_idx, String member_id) {
		return mapper.setProdCancelBids(prod_idx, member_id);
	}

	@Override
	public Integer chkMember(String faulty_mamner_id) {
		return mapper.chkMember(faulty_mamner_id);
	}

	@Override
	public Integer chkMemberFaulty(String report_mamner_id, String faulty_mamner_id) {
		return mapper.chkMemberFaulty(report_mamner_id, faulty_mamner_id);
	}

	@Override
	public Integer setAddMemberFaulty(MemberFaulty memberFaulty) {
		return mapper.setAddMemberFaulty(memberFaulty);
	}

	@Override
	public Integer setMemberFaultyCnt(String faulty_mamner_id, Integer countNum) {
		return mapper.setMemberFaultyCnt(faulty_mamner_id, countNum);
	}

	@Override
	public Integer setCancelMemberFaulty(String report_mamner_id, String faulty_mamner_id) {
		return mapper.setCancelMemberFaulty(report_mamner_id, faulty_mamner_id);
	}

	@Override
	public Member selMemberIdInfo(String member_id) {
		return mapper.selMemberIdInfo(member_id);
	}

	@Override
	public Member selMemberNickInfo(String member_nickName) {
		return mapper.selMemberNickInfo(member_nickName);
	}

	@Override
	public List<Product> selProdIdInfo(String member_id) {
		return mapper.selProdIdInfo(member_id);
	}

	@Override
	public List<Product> selProdNumInfo(int prod_idx) {
		return mapper.selProdNumInfo(prod_idx);
	}

	@Override
	public List<Product> selProdNameInfo(String prod_name) {
		return mapper.selProdNameInfo(prod_name);
	}

	@Override
	public List<MemberFaultyInfo> selFaultyList() {
		return mapper.selFaultyList();
	}

	@Override
	public List<Integer> selCateTopList() {
		return mapper.selCateTopList();
	}

	@Override
	public List<Cate> selCateSubList(Integer cate_main) {
		return mapper.selCateSubList(cate_main);
	}

	@Override
	public List<Banner> selMainTopBanner(String cate) {
		return mapper.selMainTopBanner(cate);
	}

	@Override
	public Integer setMainTopBanner(Banner banner) {
		return mapper.setMainTopBanner(banner);
	}

	@Override
	public Integer addBuyInfo(BuyInfo buyInfo) {
		return mapper.addBuyInfo(buyInfo);
	}

	@Override
	public Integer uptMainTopBanner(Banner banner) {
		return mapper.uptMainTopBanner(banner);
	}

	@Override
	public List<Product> getProdBidsList(String member_id, Integer page, Integer pagesize) {
		return mapper.getProdBidsList(member_id, page, pagesize);
	}

	@Override
	public Integer uptMemberStatus(String member_id, Integer status) {
		return mapper.uptMemberStatus(member_id, status);
	}

	@Override
	public List<BuyListInfo> selBuyList(String member_id, Integer page, Integer pagesize) {
		return mapper.selBuyList(member_id, page , pagesize);
	}

	@Override
	public Integer uptBuyInfo(Integer buy_idx, Integer state) {
		return mapper.uptBuyInfo(buy_idx, state);
	}

	@Override
	public List<BuyListInfo> selSellList(String member_id, Integer page, Integer pagesize) {
		return mapper.selSellList(member_id, page, pagesize);
	}

	@Override
	public Integer selBuyCount(String member_id) {
		return mapper.selBuyCount(member_id);
	}

	@Override
	public Integer selSellCount(String member_id) {
		return mapper.selSellCount(member_id);
	}

	@Override
	public Integer getProdBidsCount(String member_id) {
		return mapper.getProdBidsCount(member_id);
	}

	@Override
	public List<Product> selProdSelfInfo(String member_id, Integer page, Integer pagesize) {
		return mapper.selProdSelfInfo(member_id, page, pagesize);
	}

	@Override
	public Integer selProdSelfCount(String member_id) {
		return mapper.selProdSelfCount(member_id);
	}

	@Override
	public Integer addAdmin(String member_id) {
		return mapper.addAdmin(member_id);
	}

	@Override
	public Integer delAdmin(String member_id) {
		return mapper.delAdmin(member_id);
	}

	@Override
	public Integer chkAdmin(String member_id) {
		return mapper.chkAdmin(member_id);
	}

	@Override
	public List<String> listAdmin() {
		return mapper.listAdmin();
	}

}

package com.ezen.farmocean.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.admin.dto.MemberFaulty;
import com.ezen.farmocean.admin.dto.MemberFaultyInfo;
import com.ezen.farmocean.admin.mapper.AdminJsonRestMapper;
import com.ezen.farmocean.member.dto.Member;
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

	

}

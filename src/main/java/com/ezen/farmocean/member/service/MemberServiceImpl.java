package com.ezen.farmocean.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.member.dto.BuyMember;
import com.ezen.farmocean.member.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	
@Autowired
MemberMapper mapper;
	
@Override
public List<BuyMember> getList() {
	
	return mapper.getList();
}

@Override
public Integer insert(BuyMember user) {
	
	return mapper.insert(user);
}


	
}

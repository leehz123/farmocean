package com.ezen.farmocean.prod.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.prod.mapper.EtcMapper;

@Service
public class EtcServiceImpl implements EtcService {

	@Autowired
	EtcMapper etc;
	
	@Override
	public String getMemberImageById(String member_id) {
		return etc.getMemberImageById(member_id);
	}
	
}

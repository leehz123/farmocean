package com.ezen.farmocean.cs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.farmocean.cs.dto.Cate;
import com.ezen.farmocean.cs.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper mapper;
	
	@Override
	public List<Cate> getGateList() {
		return mapper.getCateList();		
	}

}

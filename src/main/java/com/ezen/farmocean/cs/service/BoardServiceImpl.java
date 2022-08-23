package com.ezen.farmocean.cs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ezen.farmocean.cs.dto.BoardCate;
import com.ezen.farmocean.cs.dto.CsBoard;
import com.ezen.farmocean.cs.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper mapper;
	
	@Override
	public List<BoardCate> getGateList() {
		return mapper.getCateList();		
	}

	@Override
	public List<CsBoard> getBoardList() {
		return mapper.getBoardList();
	}

	@Override
	public Integer setBoardIns(CsBoard csboard) {		
		return mapper.setBoardIns(csboard);
	}

	@Override
	public CsBoard getBoardInfo(Integer board_idx) {
		return mapper.getBoardInfo(board_idx);
	}

	@Override
	public void setBoardCount(Integer board_idx) {
		mapper.setBoardCount(board_idx);
	}

}

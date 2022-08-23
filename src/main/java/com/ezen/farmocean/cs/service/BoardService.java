package com.ezen.farmocean.cs.service;

import java.util.List;

import com.ezen.farmocean.cs.dto.Cate;
import com.ezen.farmocean.cs.dto.CsBoard;

public interface BoardService {
	
	public List<Cate> getGateList();
	public List<CsBoard> getBoardList();

}

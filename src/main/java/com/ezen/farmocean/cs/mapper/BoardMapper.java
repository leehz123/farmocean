package com.ezen.farmocean.cs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.cs.dto.BoardCate;
import com.ezen.farmocean.cs.dto.CsBoard;

public interface BoardMapper {
	
	// 게시판 카테고리 목록
	public List<BoardCate> getCateList();	
	// 게시글 목록(페이징)
	public List<CsBoard> getBoardList(@Param("page") Integer page,@Param("pagesize") Integer pagesize);
	// 글 등록
	public Integer setBoardIns(CsBoard csboard);
	// 조회수 증가
	public void setBoardCount(Integer board_idx);
	// 글 불러오기
	public CsBoard getBoardInfo(Integer board_idx);
	// 총 게시글 수
	public Integer getBoardCount();
	
}

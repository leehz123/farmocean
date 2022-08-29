package com.ezen.farmocean.cs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.farmocean.cs.dto.BoardCate;
import com.ezen.farmocean.cs.dto.CsBoard;

public interface BoardMapper {
	
	// �Խ��� ī�װ� ���
	public List<BoardCate> getCateList();	
	// �Խñ� ���(����¡)
	public List<CsBoard> getBoardList(@Param("page") Integer page,@Param("pagesize") Integer pagesize);
	// �� ���
	public Integer setBoardIns(CsBoard csboard);
	// ��ȸ�� ����
	public void setBoardCount(Integer board_idx);
	// �� �ҷ�����
	public CsBoard getBoardInfo(Integer board_idx);
	// �� �Խñ� ��
	public Integer getBoardCount();
	
}

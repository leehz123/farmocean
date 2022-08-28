package com.ezen.farmocean.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ezen.farmocean.cs.dto.CsBoard;
import com.ezen.farmocean.cs.service.BoardService;
import com.ezen.farmocean.cs.service.CommonFunction;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class BoardController {
	
	// ���ذ�
	// ���� ���� ��� => ��� ��η� ã�Ƽ� ��ä ������Ʈ�� ���� ��η� ����ǰ�
	// �α��� ���� set, get	[22.08.26]
	// ��ǰ ������ ������ �������� �����ϰ�(���ο� ǥ�õ� ��ǰ ���� �ҷ����� ����) => ���̺� �߰� �� ���� ����
	// �α��� ���� ajax ����
	//		1. �α��� ������ �α��� ��ư ����
	//		2. ��Ͽ� �۵�� ����
	//		3. �α��� ���� �ʿ��Ѱ��� �α��� ���� üũ
	
	@Autowired
	BoardService service;
	
	@Autowired
	CommonFunction cf;
	
	@GetMapping({"/board", ""})
	public String boardRoot(Model model) {	
		return "redirect:/board/notice";		
	}
	
	@GetMapping("/board/notice")
	public void boardNotice(Model model) {	
		model.addAttribute("boards", service.getBoardList());		
	}
	
	@GetMapping("/board/insert")
	public void boardInsert(Model model) {
		model.addAttribute("catagorys", service.getGateList());		
	}
	
	@PostMapping("/board/insert")
	public String boardInsert(CsBoard board) {
		
		board.setBoard_header(0);
		board.setBoard_writer("softdol");
		log.info(board);
		log.info(board.getBoard_memo().length());
		
		if(cf.chkNull(board.getBoard_title()) || cf.chkNull(board.getBoard_memo())) {
			return "redirect:insert";
		}
		
		if(service.setBoardIns(board) > 0) {
			return "redirect:notice";
		}else {
			return "redirect:insert";
		}
	}
	
	@GetMapping("/board/view/{board_idx}")
	public String boardView(@PathVariable Integer board_idx, Model model) {
		service.setBoardCount(board_idx);
		CsBoard board = service.getBoardInfo(board_idx);
//		log.info(board.getBoard_memo());
//		log.info(cf.chgHtml(board.getBoard_memo()));
		board.setBoard_memo(cf.chgHtml(board.getBoard_memo()));
		board.setBoard_memo(board.getBoard_memo().replace((char)(49824), ' '));
		log.info((char)(49824));
		log.info(board.getBoard_memo());
		model.addAttribute("board", board);
		return "board/view";
	}
	
	@GetMapping("/board/notice_insert")
	public void boardNoticeHtmlIns() {
	}

}

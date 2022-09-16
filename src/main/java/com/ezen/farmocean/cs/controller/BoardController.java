package com.ezen.farmocean.cs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ezen.farmocean.cs.dto.CsBoard;
import com.ezen.farmocean.cs.service.BoardService;
import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.member.dto.LoginMember;

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
	
	@Autowired
	HttpServletRequest req;
		
	@GetMapping({"/board", ""})
	public String boardRoot(Model model) {	
		return "redirect:/board/notice";		
	}
	
	@GetMapping({"/board/notice/", "/board/notice"})
	public String boardNotice() {	
		return "redirect:/board/notice/1";		
	}
	
	/*
	 * �������� ���(����¡)
	 */
	@GetMapping("/board/notice/{page}")
	public String boardNotice(Model model, @PathVariable Integer page) {
		
		int pageSize = 5;
		
		model.addAttribute("boards", service.getBoardList(page, pageSize));
		
		int totalCnt = service.getBoardCount();
		
		int pageLsit = totalCnt % pageSize == 0 ? totalCnt / pageSize : totalCnt / pageSize + 1;
		
		model.addAttribute("page", page);
		model.addAttribute("pageLsit", pageLsit);
		
		return "board/notice";
	}
	
	/*
	 * �������� ��� ���
	 */
	@GetMapping("/board/insert")
	public void boardInsert(Model model) {
		model.addAttribute("catagorys", service.getGateList());		
	}
	
	/**
	 * �������� ���
	 * @param board
	 * @return
	 */
	@PostMapping("/board/insert")
	public String boardInsert(CsBoard board) {
		
		LoginMember mInfo = cf.loginInfo(req);
		
		if(mInfo.getMember_id() == null) {
			return "redirect:insert";
		}
		
		board.setBoard_header(0);
		board.setBoard_writer(mInfo.getMember_id());
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
	
	/**
	 * �Խñ� ����
	 * @param board_idx
	 * @param model
	 * @return
	 */
	@GetMapping("/board/view/{board_idx}")
	public String boardView(@PathVariable Integer board_idx, Model model, Integer page) {		
		service.setBoardCount(board_idx);
		CsBoard board = service.getBoardInfo(board_idx);
		board.setBoard_memo(cf.chgHtml(board.getBoard_memo()));
		
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		return "board/view";
	}
	
	@GetMapping("/board/notice_insert")
	public void boardNoticeHtmlIns() {
	}
	
	@GetMapping("/board/apitest")
	public void boardApiTest() {
		
	}

}

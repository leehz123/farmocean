package com.ezen.farmocean.cs.com.ezen.controller;

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
	
	@Autowired
	BoardService service;
	
//	@Autowired
//	CommonFunction cf;
	
	
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
		
//		if(cf.chkNull(board.getBoard_title()) || cf.chkNull(board.getBoard_memo())) {
//			return "redirect:insert";
//		}
		
		if(service.setBoardIns(board) > 0) {
			return "redirect:notice";
		}else {
			return "redirect:insert";
		}	
	}
	
	@GetMapping("/board/view/{board_idx}")
	public String boardView(@PathVariable Integer board_idx, Model model) {
		service.setBoardCount(board_idx);
		model.addAttribute("board", service.getBoardInfo(board_idx));
		return "board/view";
	}

}

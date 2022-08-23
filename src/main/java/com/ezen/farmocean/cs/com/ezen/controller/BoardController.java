package com.ezen.farmocean.cs.com.ezen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ezen.farmocean.cs.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class BoardController {
	
	@Autowired
	BoardService service;
	
	
	@GetMapping("/board/notice")
	public void boardNotice(Model model) {
		
		model.addAttribute("boards", service.getBoardList());
		
	}
	

}

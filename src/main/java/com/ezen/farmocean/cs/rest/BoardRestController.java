package com.ezen.farmocean.cs.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.cs.dto.Cate;
import com.ezen.farmocean.cs.service.BoardService;

@RestController
public class BoardRestController {
	
	@Autowired
	BoardService board;
	
	@GetMapping(value = "/board/catelist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Cate> boardCateList(){
		return board.getGateList();
	}

}

package com.ezen.farmocean.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.farmocean.mypage.service.MessageService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	MessageService service;
	
	@Autowired
	public MypageController(MessageService service) {
		this.service = service;
	}
	
	@GetMapping("/list")
	public void MessageList(Model model) {
		model.addAttribute("messageList", service.getList());
	}
	
	@GetMapping("mylist")
	public void MyMessageList(String id, Model model) {
		log.info(id);
		//model.addAttribute("myList", service.getMyList(id));
	}

}

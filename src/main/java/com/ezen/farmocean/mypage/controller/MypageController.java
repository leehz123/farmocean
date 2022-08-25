package com.ezen.farmocean.mypage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/main")
	public void mainPage(HttpSession session) {
		session.setAttribute("userid", "think");
		session.setAttribute("check", "sell");
	}
	
	@GetMapping("/list")
	public void messageList(Model model) {
		model.addAttribute("messageList", service.getList());
	}
	
	@GetMapping("mylist")
	public void myMessageList(HttpSession session, Model model) {
		//log.info(session.getAttribute("userid"));
		
		String id = (String) session.getAttribute("userid");
		model.addAttribute("myList", service.getMyList(id));
	}
	
	@GetMapping("changeinfo")
	public void changeUserInfo(HttpSession session, Model model) {
		//log.info(session.getAttribute("check"));
		
		String userid = (String) session.getAttribute("userid");
		String check = (String) session.getAttribute("check");
		
		if (check.equals("sell")) {
			
		}else {
			
		}
	}
	
}

package com.ezen.farmocean.mypage.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
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
	
	// 쪽지 메인 페이지
	@GetMapping("/main")
	public void mainPage(HttpSession session) {
		
		LoginMember member = new LoginMember();
		
		member.setMember_id("kingdom");
		member.setMember_name("아서");
		member.setMember_nickName("엑스칼리버");
		member.setMember_pw("1234");
		member.setMember_type("B");
		
//		member.setMember_id("solo");
//		member.setMember_name("홍길동");
//		member.setMember_nickName("땅이나다");
//		member.setMember_pw("ase123!@#");
//		member.setMember_type("S");
				
		session.setAttribute("member", member);

	}
	
	// 전체 쪽지 리스트 (test로 만듬)
	@GetMapping("/list")
	public void messageList(Model model) {
		model.addAttribute("messageList", service.getList());
	}
	
	// 내가 받은 쪽지함
	@GetMapping("mylist")
	public void myMessageList(HttpSession session, Model model) {
		//log.info(session.getAttribute("userid"));
		
		String id = (String) session.getAttribute("userid");
		model.addAttribute("myList", service.getMyList(id));
	}
	
	// 내가 보낸 쪽지함
	@GetMapping("mysendlist")
	public void mySendList(HttpSession session, Model model) {
		//log.info(session.getAttribute("userid"));
		
		String id = (String) session.getAttribute("userid");
		model.addAttribute("mysendlist", service.getMySendList(id));
	}
	
	// 회원 정보 수정
	@GetMapping("changeinfo")
	public void changeUserInfo(HttpSession session, Model model) {
		
		LoginMember member = (LoginMember) session.getAttribute("member");
		
		model.addAttribute("memberinfo", service.getMember(member.getMember_id()));
		model.addAttribute("check", member.getMember_type());

//		log.info(member.getMember_id());
//		log.info(member.getMember_name());
//		log.info(member.getMember_nickName());
//		log.info(member.getMember_pw());
//		log.info(member.getMember_type());
		
	}	
	
	@PostMapping("changeinfo")
	public String changeUserInfomation(Member member) {
		
		if (member.getMember_type().equals('S')) {			
			service.getUpdateinfo(member);
		} else {
			service.getUpdateinfoB(member);
		}
		
//		log.info(member.getMember_id());
//		log.info(member.getMember_name());
//		log.info(member.getMember_nickName());
//		log.info(member.getMember_pw());
//		log.info(member.getMember_type());
		
		
		return "redirect:/mypage/main";
	}
	
	// 프로필 이미지 변경
	@PostMapping("changeimg")
	public String changeUserImg() {
		
		
		return "redirect:/mypage/changeinfo";
	}
	
}

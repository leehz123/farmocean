package com.ezen.farmocean.follow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.farmocean.follow.service.FollowService;
import com.ezen.farmocean.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/Sell")
@Controller
public class SellmemberController {
	
	@Autowired
	MemberService service;
	
	@Autowired
	FollowService service2;
	
	@GetMapping("/member/{idx}")
	public String getSellMember(Model model, @PathVariable String idx) {
		
		log.info("idx" +idx);
		log.info("서비스"+service.getMember(idx));
		
		
		
		model.addAttribute("follower", service2.getFollowerList(service.getMember(idx).getMember_id()));
		
		log.info("팔로우"+ service2.getFollowerList(service.getMember(idx).getMember_id()));
		model.addAttribute("sellMember", service.getMember(idx));
		
		
		
		return "follow/sellMember";
	}
	
	
}

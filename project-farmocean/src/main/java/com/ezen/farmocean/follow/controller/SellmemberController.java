package com.ezen.farmocean.follow.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.farmocean.follow.dto.Follow;
import com.ezen.farmocean.follow.service.FollowService;
import com.ezen.farmocean.member.dto.Member;
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
	public String getSellMember(Model model, @PathVariable String idx) throws Exception {
		
		log.info("idx_sell : " + idx);
		Member member = service.getMember(idx);
		//log.info(service2.getFollowerList(service.getMember(idx).getMember_id()));
		List<Follow> follow = service2.getFollowerList(idx);
		
		ArrayList<String> list = new ArrayList<>();
		
		for(int i = 0; i < follow.size(); i++) {
			Member followerMember = service.getMember(follow.get(i).getFollower_id());
			list.add(followerMember.getMember_nickName());
		}
		
			
		model.addAttribute("follower", service2.getFollowerList(idx));
		
		model.addAttribute("followerNickname", list);
		
		model.addAttribute("sellMember", service.getMember(idx));
		log.info("member_sell :" + member);
		if(member != null) {
			model.addAttribute("name", member.decrypt(member.getMember_name()));
			model.addAttribute("phoneNum", member.decrypt(member.getMember_phoneNum()));
			model.addAttribute("email", member.decrypt(member.getMember_email()));
			model.addAttribute("address",  member.decrypt(member.getMember_address()));
			model.addAttribute("accountNum",  member.decrypt(member.getMember_accountNum()));
		}

	
		return "follow/sellMember";
	}
	
	
}

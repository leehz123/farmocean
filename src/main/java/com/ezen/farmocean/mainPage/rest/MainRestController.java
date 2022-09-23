package com.ezen.farmocean.mainPage.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.mainPage.mapper.ProductListMapper;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.service.MemberService;
import com.ezen.farmocean.prod.dto.ProdImg;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class MainRestController {
	
	@Autowired
	CommonFunction cf;
	
	@Autowired
	HttpServletRequest req;
	
	@Autowired
	MemberService serviceMm;
	
	@GetMapping(value="/user/profileImg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Member getProflieImg() {
		
		LoginMember mInfo = cf.loginInfo(req);
		
		if (cf.chkNull(mInfo.getMember_id())) {
			return null;
		}
		
		return serviceMm.getMember(mInfo.getMember_id());
	}

}

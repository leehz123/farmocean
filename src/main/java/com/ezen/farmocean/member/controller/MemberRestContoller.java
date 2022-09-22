package com.ezen.farmocean.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.service.Encrypt;
import com.ezen.farmocean.member.service.MemberService;
import com.ezen.farmocean.member.service.memberFunction;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/member")
@RestController
public class MemberRestContoller {

	@Autowired
	MemberService service;

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Member> listMember() {

		return service.getList();
	}

	@GetMapping(value = "/nickNameCheck/{member_nickName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer nickNameCheck(@PathVariable String member_nickName) {
		Member existence = service.nickNameCheck(member_nickName);

		if (existence == null) {
			return 1;
		} else {
			return 2;
		}
	}

	@GetMapping(value = "/pwAvailable/{pw}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer pwCheck(@PathVariable String pw) {

		if (memberFunction.chkPatternPassword(pw) == true) {
			return 1;
		} else {
			return 2;
		}
	}

	@GetMapping(value = "/idAvailable/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer idCheck(@PathVariable String id) {

		if (memberFunction.chkPatternId(id) == true) {
			return 1;
		} else {
			return 2;
		}
	}

	// 회원가입
	@PostMapping(value = "/insert/member", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Member> insertUser(@RequestBody Member member) {

		if (member.getMember_id() == null || member.getMember_id().trim().equals("") || member.getMember_name() == null
				|| member.getMember_email() == null) {
			return ResponseEntity.badRequest().build();
		}

		try {
			member.setEnc();

			service.insert(member);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping(value = "/insert/naver", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Member> insertNaver(@RequestBody LoginMember member, HttpServletRequest request) {

		if (member.getMember_id() == null || member.getMember_id().trim().equals("")
				|| member.getMember_name() == null) {
			return ResponseEntity.badRequest().build();
		}

		try {

			HttpSession session = request.getSession();
			session.setAttribute("loginId", member);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping(value = "/idsearch", produces = MediaType.TEXT_PLAIN_VALUE)
	public String idSearchPost(@RequestBody Member member) throws Exception {
		member.setMember_name(member.encrypt(member.getMember_name()));
		member.setMember_email(member.encrypt(member.getMember_email()));
		Member searchMember = service.idSearch(member);
		String returnMessage = "undefined";
		if (searchMember == null) {
			return returnMessage;

		} else {

			return searchMember.getMember_id();
		}

	}

	@PostMapping(value = "/pwsearch", produces = MediaType.TEXT_PLAIN_VALUE)
	public String pwSearchPost(@RequestBody Member member) throws Exception {

		member.setMember_email(member.encrypt(member.getMember_email()));
		Member searchMember = service.pwSearch(member);
		String returnMessage = "undefined";
		if (searchMember == null) {

			return returnMessage;

		} else {

			return searchMember.getMember_pw();
		}

	}

	@GetMapping(value = "/changePw/{pw}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer pwChangeCheck(@PathVariable String pw, HttpServletRequest request) {
		HttpSession session = request.getSession();
		LoginMember member = (LoginMember) session.getAttribute("loginId");

		String encPw = new Encrypt().pwEncrypt(pw);

		if (member.getMember_pw().equals(encPw)) {
			return 1;
		} else {
			return 2;
		}

	}

	@PostMapping(value = "/memberPwChange", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Member> memberPwChange(@RequestBody Member member, HttpServletRequest request) {

		if (member.getMember_pw() == null || member.getMember_pw().trim().equals("")) {
			return ResponseEntity.badRequest().build();
		}

		try {

			HttpSession session = request.getSession();
			LoginMember loginedMember = (LoginMember) session.getAttribute("loginId");
			loginedMember.setMember_pw(new Encrypt().pwEncrypt(member.getMember_pw()));
			
			service.memberPwChange(loginedMember);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}

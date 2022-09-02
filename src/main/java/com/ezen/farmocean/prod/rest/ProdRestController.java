package com.ezen.farmocean.prod.rest;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.prod.dto.JoinReviewMember;
import com.ezen.farmocean.prod.dto.ProductComment;
import com.ezen.farmocean.prod.mapper.JoinReviewMemberMapper;
import com.ezen.farmocean.prod.service.EtcServiceImpl;
import com.ezen.farmocean.prod.service.ProdCommentServiceImpl;
import com.ezen.farmocean.prod.service.ProdReviewServiceImpl;
import com.ezen.farmocean.prod.service.ProdServiceImpl;


@RestController
public class ProdRestController {

	@Autowired
	ProdServiceImpl prod;
	
	@Autowired
	ProdReviewServiceImpl review;
	
	@Autowired
	ProdCommentServiceImpl comment;
	
	@Autowired
	EtcServiceImpl etc;
	
	@Autowired
	JoinReviewMemberMapper jrm;
	
	@GetMapping(value="/prod/temp_login", produces = MediaType.APPLICATION_JSON_VALUE)
	public LoginMember tempLogin(HttpServletRequest req){
		LoginMember member = new LoginMember();
	
		HttpSession session = req.getSession();
	      member.setMember_id("kingbambbang");
	      member.setMember_name("이회원");
	      member.setMember_nickName("왕밤빵");
	      member.setMember_pw("asdf1234");
	      member.setMember_type("B");
	      
	      session.setAttribute("loginId", member);
	      
	      return (LoginMember)session.getAttribute("loginId");
	}


	   @GetMapping(value = "/prod/temp_logout", produces = MediaType.APPLICATION_JSON_VALUE)
	   public LoginMember tempLogout(HttpServletRequest req) {
	      
	      HttpSession session = req.getSession();
	      session.removeAttribute("loginId");
	      
	      return (LoginMember)session.getAttribute("loginId");
	   }

	   
	   
	   
	   
	   @PostMapping(value = "/prod/insert_review", produces = MediaType.APPLICATION_JSON_VALUE)
	   public String insertReview(@RequestBody ProductComment productComment) {
		   return comment.insertComment(productComment.getProd_idx(), productComment.getMember_id(), productComment.getComment_content(), productComment.getComment_secret()).toString();
	   }
	   
	   
	   @GetMapping(value="/prod/select_prod_comment/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public List<ProductComment> selectComment(@PathVariable("prod_idx") Integer prod_idx) {		   
		   //req.setAttribute("test", "test going well?"); 이건 안 됨 페이지 로드 된 다음에 실행되는 ajax라 ... 
		   
		   List<ProductComment> commentList = comment.getCommentListByProdIdx(prod_idx);
		   return commentList;
	   }
	   
	   @GetMapping(value="/prod/select_prod_review/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public List<JoinReviewMember> selectReview(@PathVariable("prod_idx") Integer prod_idx) {
		    
		   System.out.println(jrm.getReviewMemberListByProdIdx(prod_idx));
		   return jrm.getReviewMemberListByProdIdx(prod_idx);
	   }
	   
	   @GetMapping(value="/prod/get_member_image/{member_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public String getMemberProfile(@PathVariable("member_id") String member_id) {
		   System.out.println("일단 레컨 도착");
		   System.out.println("여기까지는? : " + etc.getMemberImageById(member_id));
		   return etc.getMemberImageById(member_id);
	   }
	   
	   
	   
}


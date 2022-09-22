package com.ezen.farmocean.mypage.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezen.farmocean.admin.service.JsonProdService;
import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.follow.service.FollowService;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.service.MemberService;
import com.ezen.farmocean.mypage.service.MessageService;
import com.ezen.farmocean.prod.service.ProdCommentService;
import com.ezen.farmocean.prod.service.ProdReviewService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	@Autowired
	MemberService memberService;
	
	MessageService service;
	
	@Autowired
	FollowService followService;
	
	@Autowired
	CommonFunction cf;
	
	@Autowired
	HttpServletRequest req;
	
	@Autowired
	JsonProdService service2;
	
	@Autowired
	ProdCommentService service1;
	
	@Autowired
	ProdReviewService service3;
	
	@Autowired
	public MypageController(MessageService service) {
		this.service = service;
	}
	
	
	// ���� ������
	@GetMapping("/main")
	public String mainPage(HttpSession session, Model model) {
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "/mypage/notLogin";
		}
		LoginMember member = (LoginMember) session.getAttribute("loginId");
		
		log.info("��� ���̵�: " + member.getMember_id());
		
		Member myMember = service.getMember(member.getMember_id()).get(0);
		
		// ��ȣȭ 
		myMember.setDec();
		
		log.info(myMember);
		
		List<Member> myMembers = new ArrayList<>();
		
		myMembers.add(myMember);
		
		model.addAttribute("myMembers", myMembers);
		
		return "/mypage/main";
				
//		session.setAttribute("member", member);

	}
	
	// ��ü ���� ����Ʈ (test�� ����)
	@GetMapping("/list")
	public void messageList(Model model) {
		model.addAttribute("messageList", service.getList());
	}
	
	// ���� ���� ���� ����
	@GetMapping("/showMessage")
	public String showMessage(HttpSession session, Model model , String id, int check) {
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "/mypage/notLogin";
		}
		
		log.info("Ȯ��id: " + id);
		
		if (check == 0) {
			service.getUpdateReadMyMessage(id);			
			service.getUpdateReadMyMessage2(id);			
		}
		
		log.info("Ȯ��id�� ���� �г��� ã��: " + service.getReadMyMessage(id).get(0).getSender_id());
		
		//log.info("Ȯ��id�� ���� ���̵� ã��: " + service.getNickNameMember(service.getReadMyMessage(id).get(0).getSender_id()).get(0).getMember_id());
		
		String ids = service.getReadMyMessage(id).get(0).getSender_id();
		
		model.addAttribute("messageList", service.getReadMyMessage(id));
		model.addAttribute("ids", ids);
		
		return "/mypage/showMessage";
	}
	
	// ���� ���� ���� ����
	@GetMapping("/showMessageB")
	public String showMessageB(HttpSession session, Model model , String id, int check) {
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "/mypage/notLogin";
		}
		
		//log.info("Ȯ��id: " + id);
		//log.info("Ȯ��: " + check);
		
		model.addAttribute("messageList", service.getReadMyMessage2(id));
		return "/mypage/showMessageB";
	}
	
	// ���� ���� ������
	@GetMapping("mylist")
	public String myMessageList(HttpSession session, Model model) {
		//log.info(session.getAttribute("userid"));
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "/mypage/notLogin";
		}
		
		LoginMember member = (LoginMember) session.getAttribute("loginId");
		
		log.info("���̵�: " + member.getMember_id());
		
		model.addAttribute("myID", member.getMember_id());
		
		return "/mypage/mylist";
	}
	
	// ���� ���� ������
	@GetMapping("mysendlist")
	public String mySendList(HttpSession session, Model model) {
		//log.info(session.getAttribute("userid"));
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "/mypage/notLogin";
		}
		
		LoginMember member = (LoginMember) session.getAttribute("loginId");
		
		log.info("���̵�: " + member.getMember_id());
		
		model.addAttribute("myID", member.getMember_id());
		
		return "/mypage/mysendlist";
	}
	
	// ���� ������ ������
	@GetMapping("sendMessage")
	public String sendMessagePage(HttpSession session, Model model) {
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "/mypage/notLogin";
		}
		
		
		return "/mypage/sendMessage";
	}
	
	// ���� ������ (Ư�� ��� ����)
	@GetMapping("sendMessages")
	public String sendToMessagePage(HttpSession session, String id, Model model) {
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "/mypage/notLogin";
		}
		
		log.info("�г���: " + id);
		
		model.addAttribute("sendMessageId", service.getMember(id));
		
		return "/mypage/sendMessage2";
	}
	
	// ���� ������
	@PostMapping("sendMessage")
	public String sendMessage(String id, String title, String content, HttpSession session) {
		
		LoginMember member = (LoginMember) session.getAttribute("loginId");
		
		log.info("id:" + id);
		log.info("title:" + title);
		log.info("content:" + content);
		log.info("myId:" + member.getMember_id());
		
		String myId = member.getMember_id();
		
		service.getSendMessage(member.getMember_id(), id, title, content, member.getMember_id());
		service.getSendMessage2(member.getMember_id(), id, title, content, member.getMember_id());
		
		return "/mypage/closePage";
	}
	
	// ���� �����ϱ� (���� ���� ����)
	@PostMapping("deleteMessage")
	public String deleteMessage(String message_id) {
		
		log.info("message_id:" + message_id);
		
		service.getDeleteMessage(message_id);
		
		return "redirect:/mypage/mylist";
	}
	
	// ���� �����ϱ� (���� ���� ����)
	@PostMapping("deleteSendMessage")
	public String deleteSendMessage(String message_id) {
		
		log.info("message_id:" + message_id);
		
		service.getDeleteSendMessage(message_id);
		
		return "redirect:/mypage/mysendlist";
	}
	
	
	
	// ȸ�� ���� ����
	@GetMapping("changeinfo")
	public String changeUserInfo(HttpSession session, Model model) throws Exception {
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "/mypage/notLogin";
		}
		
		LoginMember member = (LoginMember) session.getAttribute("loginId");
		
		log.info(member.getMember_id());
		log.info(member.getMember_name());
		log.info(member.getMember_nickName());
		log.info(member.getMember_pw());
		log.info(member.getMember_type());
		
		Member member2 = service.getMember(member.getMember_id()).get(0);
		
		// ��ȣȭ
		member2.setDec();
		
		List<Member> members = new ArrayList<>();
		
		members.add(member2);
		
		model.addAttribute("members", members);

		if (member.getMember_type().equals("S")) {			
			return "/mypage/changeinfo";
		} else {
			return "/mypage/changeinfoB";
		}
		
	}	
	
	@PostMapping("changeinfo")
	public String changeUserInfomation(HttpSession session, Member member) {
		
		LoginMember members = (LoginMember) session.getAttribute("loginId");
		
		log.info("Member_accountNum: " + member.getMember_accountNum());
		log.info("Member_address: " + member.getMember_address());
		log.info("Member_id: " + member.getMember_id());
		log.info("Member_name: " + member.getMember_name());
		log.info("nickName: " + member.getMember_nickName());
		log.info("Member_pw(): " + member.getMember_pw());
		log.info("type: " + member.getMember_type());
		
		
		// ��ȣȭ 
		member.setEnc();
		
		log.info("Member_accountNum: " + member.getMember_accountNum());
		log.info("Member_address: " + member.getMember_address());
		log.info("Member_id: " + member.getMember_id());
		log.info("Member_name: " + member.getMember_name());
		log.info("nickName: " + member.getMember_nickName());
		log.info("Member_pw(): " + member.getMember_pw());
		log.info("type: " + member.getMember_type());
		
		if (members.getMember_type().equals("S")) {			
			log.info('s');
			service.getUpdateinfo(member);
		} else {
			log.info('b');
			service.getUpdateinfoB(member);
		}
		
		
		
		return "redirect:/mypage/main"; 
	}
	
	// ������ �̹��� �����ϱ�
	@GetMapping("changeimg")
	public String changeUserImg(HttpSession session, Model model) {
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "/mypage/notLogin";
		}
		
		LoginMember member = (LoginMember) session.getAttribute("loginId");
		
		model.addAttribute("memberinfo", service.getMember(member.getMember_id()));
		
		return "/mypage/changeimg";
		
	}	
	
	
	// ������ �̹��� ����
	@PostMapping("changeimg")
	public String changeUserImg(@RequestParam("fileInput") MultipartFile file, Member member, String checkImg) {
		
		//log.info("checkImg: " + checkImg);
		
		if (checkImg.equals("basic")) {
			service.getUpdateImg("profile_basic_image.jpg", member.getMember_id());
			return "/mypage/main";
		}
		
		if (file.isEmpty()) {
			log.error("����ִ� ������ ������ �� �����ϴ�.");
			return"redirect:/mypage/main";
		}
	
		
		//  ���� ���� ���
		Path rootLocation = Paths.get("../../spring repository/project-farmocean/src/main/webapp/resources/image/mypage");
		
		
		try {
			// ���丮 ����
			Files.createDirectory(rootLocation);
			 System.out.println(rootLocation + " ���丮�� �����Ǿ����ϴ�.");
		} catch (FileAlreadyExistsException e) {
			System.out.println("���丮�� �̹� �����մϴ�");
		} catch (NoSuchFileException e) {
			System.out.println("���丮 ��ΰ� �������� �ʽ��ϴ�");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		log.info("id: " + member.getMember_id());
		log.info("rootLocation: " + rootLocation);
		log.info("rootLocation.toAbsolutePath(): " + rootLocation.toAbsolutePath());
		
		
		UUID uuid = UUID.randomUUID();
		
		log.info("uuid: " + uuid);
		
		Path destinationFile = rootLocation.resolve(
				Paths.get(uuid + file.getOriginalFilename())).normalize();
		
		log.info("destinationFile: " + destinationFile);
		
							// ����Ǵ� ���� �̸� uuid + file.getOriginalFilename()
		service.getUpdateImg(uuid + file.getOriginalFilename(), member.getMember_id());
		
		try (InputStream in = file.getInputStream()){

			Files.createDirectories(destinationFile);

			Files.copy(in, destinationFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "/mypage/main";
	}


	// �Ǹ� ��ǰ ��� 1������
	@GetMapping(value="/sellgoods/{iPage}", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
	public String sellgoodsList(@PathVariable Integer iPage, HttpSession session, Model model) {
	
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "/mypage/notLogin";
		}
		
		log.info("iPage: " + iPage);
	
		LoginMember member = (LoginMember) session.getAttribute("loginId");
	
		model.addAttribute("memberinfo", service.getMember(member.getMember_id()));
		model.addAttribute("iPages", iPage);
	
		return "/mypage/sellgoods";
	}
	
	// ���� ��ǰ ��� 1������
	@GetMapping(value="likegoods/{iPage}", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
	public String likegoods(@PathVariable Integer iPage, HttpSession session, Model model) {
	
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "/mypage/notLogin";
		}
	
		LoginMember member = (LoginMember) session.getAttribute("loginId");
	
		model.addAttribute("memberinfo", service.getMember(member.getMember_id()));
		model.addAttribute("iPages", iPage);
	
		return "/mypage/likegoods";
	}
	
	// ���� ��ǰ ���
	@GetMapping(value = "/deleteLikegoods/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String setCancelProdBids(@PathVariable Integer prod_idx){
		
		LoginMember mInfo = cf.loginInfo(req);
		
		Map<String, String> result = new HashMap<>();
		
		if(mInfo.getMember_id() == null) {
			result.put("code", "-1");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			
			return "redirect:/mypage/likegoods/1";
		}
		
		log.info(service2.getProdUseChk(prod_idx));
		
		if(service2.getProdUseChk(prod_idx) == 0) {
			result.put("code", "-6");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			
			return "redirect:/mypage/likegoods/1";
		}
		
		if(service2.getProdBidsChk(prod_idx, mInfo.getMember_id()) > 0) {
			if(service2.setProdCancelBids(prod_idx, mInfo.getMember_id()) > 0) {
				service2.setProdCntUpBids(prod_idx, -1);
				result.put("code", "1");
				result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			}else {
				result.put("code", "-4");
				result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			}
			
			return "redirect:/mypage/likegoods/1";			
		}else {
			result.put("code", "-6");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
		}
		
		return "redirect:/mypage/likegoods/1";
	}
	
	// ��ǰ ����
	@GetMapping(value = "/hideSellgoods/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String hideSellgoods(@PathVariable String prod_idx){
		
		log.info("����: " + prod_idx);
		
		service.getHideSellgoods(prod_idx);
		
		return "/mypage/sellgoods";
	}
	
	// ��ǰ ����
	@GetMapping(value = "/hideSellgoods2/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String hideSellgoods2(@PathVariable String prod_idx){
		
		log.info("����: " + prod_idx);
		
		service.getHideSellgoods2(prod_idx);
		
		return "/mypage/sellgoods";
	}
	
	// ���� ���� ���
	@GetMapping("/myCommentList")
	public String myCommentList(HttpSession session, Model model) {
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "/mypage/notLogin";
		}
		
		LoginMember member = (LoginMember) session.getAttribute("loginId");
		
		log.info(member.getMember_id());
		
		model.addAttribute("id", member.getMember_id());
		
		return "/mypage/myCommentList";
	}
	
	// ��� ����
	@GetMapping("/deleteComment")
	public String deleteComment(int id) {
		
		service1.deleteComment(id);
		
		return "redirect:/mypage/myCommentList";
	}
	
	// ���� ���� �ı�
	@GetMapping("/myReview")
	public String myReviewList(HttpSession session, Model model) {
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "/mypage/notLogin";
		}
		
		LoginMember member = (LoginMember) session.getAttribute("loginId");
		
		log.info(member.getMember_id());
		
		model.addAttribute("id", member.getMember_id());
		
		return "/mypage/myReview";
	}
	
	// �ı� ����
	@GetMapping("/deleteReview")
	public String deleteReview(int id) {
		
		service3.deleteReviewByReviewIdx(id);
		
		return "redirect:/mypage/myReview";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
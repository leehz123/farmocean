package com.ezen.farmocean.mypage.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.mypage.dto.test;
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
	
	
	// ���� ������
	@GetMapping("/main")
	public String mainPage(HttpSession session) {
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "redirect:/member/login";
		}
		
		return "/mypage/main";
		
//		member.setMember_id("kingdom");
//		member.setMember_name("�Ƽ�");
//		member.setMember_nickName("����Į����");
//		member.setMember_pw("1234");
//		member.setMember_type("B");
		
//		member.setMember_id("solo");
//		member.setMember_name("ȫ�浿");
//		member.setMember_nickName("���̳���");
//		member.setMember_pw("ase123!@#");
//		member.setMember_type("S");
				
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
			return "redirect:/member/login";
		}
		
		//log.info("Ȯ��id: " + id);
		//log.info("Ȯ��: " + check);
		
		if (check == 0) {
			service.getUpdateReadMyMessage(id);			
			service.getUpdateReadMyMessage2(id);			
		}
		
		model.addAttribute("messageList", service.getReadMyMessage(id));
		
		return "/mypage/showMessage";
	}
	
	// ���� ���� ���� ����
	@GetMapping("/showMessageB")
	public String showMessageB(HttpSession session, Model model , String id, int check) {
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "redirect:/member/login";
		}
		
		//log.info("Ȯ��id: " + id);
		//log.info("Ȯ��: " + check);
		
		model.addAttribute("messageList", service.getReadMyMessage(id));
		return "/mypage/showMessageB";
	}
	
	// ���� ���� ������
	@GetMapping("mylist")
	public String myMessageList(HttpSession session, Model model) {
		//log.info(session.getAttribute("userid"));
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "redirect:/member/login";
		}
		
		LoginMember member = (LoginMember) session.getAttribute("loginId");
		
		model.addAttribute("myID", member.getMember_nickName());
		
		return "/mypage/mylist";
	}
	
	// ���� ���� ������
	@GetMapping("mysendlist")
	public String mySendList(HttpSession session, Model model) {
		//log.info(session.getAttribute("userid"));
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "redirect:/member/login";
		}
		
		LoginMember member = (LoginMember) session.getAttribute("loginId");
		
		model.addAttribute("myID", member.getMember_nickName());
		
		return "/mypage/mysendlist";
	}
	
	// ���� ������ ������
	@GetMapping("sendMessage")
	public String sendMessagePage(HttpSession session) {
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "redirect:/member/login";
		}
		
		return "/mypage/sendMessage";
	}
	
	// ���� ������
	@PostMapping("sendMessage")
	public String sendMessage(String id, String title, String content, HttpSession session) {
		
		LoginMember member = (LoginMember) session.getAttribute("loginId");
		
		log.info("id:" + id);
//		log.info("title:" + title);
//		log.info("content:" + content);
//		log.info("myId:" + member.getMember_id());
		
		String myId = member.getMember_id();
		
		service.getSendMessage(member.getMember_nickName(), id, title, content);
		service.getSendMessage2(member.getMember_nickName(), id, title, content);
		
		return "/mypage/closePage";
	}
	
	// ȸ�� ���� ����
	@GetMapping("changeinfo")
	public String changeUserInfo(HttpSession session, Model model) {
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "redirect:/member/login";
		}
		
		LoginMember member = (LoginMember) session.getAttribute("loginId");
		
		model.addAttribute("memberinfo", service.getMember(member.getMember_id()));

		if (member.getMember_type().equals("S")) {			
			return "/mypage/changeinfo";
		} else {
			return "/mypage/changeinfoB";
		}
		

		

//		log.info(member.getMember_id());
//		log.info(member.getMember_name());
//		log.info(member.getMember_nickName());
//		log.info(member.getMember_pw());
//		log.info(member.getMember_type());
		
	}	
	
	@PostMapping("changeinfo")
	public String changeUserInfomation(Member member) {
		
		//member.setMember_accountNum("12341234");;
		
		log.info(member.getMember_accountNum());
		log.info(member.getMember_type());
		log.info(member.getMember_address());
		
		if (member.getMember_type().equals("S")) {			
			log.info('s');
			service.getUpdateinfo(member);
		} else {
			log.info('b');
			service.getUpdateinfoB(member);
		}
		
//		log.info(member.getMember_id());
//		log.info(member.getMember_name());
//		log.info(member.getMember_nickName());
//		log.info(member.getMember_pw());
//		log.info(member.getMember_type());
		
		
		return "redirect:/mypage/main"; 
	}
	
	// ������ �̹��� �����ϱ�
	@GetMapping("changeimg")
	public String changeUserImg(HttpSession session, Model model) {
		
		if (session == null || session.getAttribute("loginId") == null || session.getAttribute("loginId").equals("")) {
			return "redirect:/member/login";
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
	
}

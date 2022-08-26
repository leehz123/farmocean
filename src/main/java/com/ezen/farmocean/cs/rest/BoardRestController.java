package com.ezen.farmocean.cs.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezen.farmocean.cs.dto.BoardCate;
import com.ezen.farmocean.cs.service.BoardService;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class BoardRestController {
	
	@Autowired
	BoardService board;
	
	
	/**
	 * �α��� ���� �ӽ� �߰�
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/board/temp_login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public LoginMember TempLogin(HttpServletRequest req) {
		
		LoginMember member = new LoginMember();
		
		HttpSession session = req.getSession();
		
		member.setMember_id("softdol");
		member.setMember_name("�ڹ�ȣ");
		member.setMember_nickName("����Ʈ");
		member.setMember_pw("");
		member.setMember_type("B");
		
		session.setAttribute("loginId", member);
		
		return (LoginMember)session.getAttribute("loginId");
				
	}
	
	@GetMapping(value = "/board/temp_logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public LoginMember TempLogout(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		session.removeAttribute("loginId");
		
		return (LoginMember)session.getAttribute("loginId");
	}
	
	
	/**
	 * �α��� ���� Ȯ�� rest
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/board/temp_login_info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public LoginMember boardTempLoginInfo(HttpServletRequest req) {
		
		LoginMember member = new LoginMember();		
		HttpSession session = req.getSession();		
		//log.info(session.getAttribute("loginId"));		
		if(session.getAttribute("loginId") != null) {
			member = (LoginMember)session.getAttribute("loginId");
		}
		
		return member;
		
	}	
	
	@GetMapping(value = "/board/catelist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<BoardCate> boardCateList(){
		return board.getGateList();
	}
//	public Map<String, List<BoardCate>> boardCateList(){
//		Map<String, List<BoardCate>> result = new HashMap<>();
//		result.put("cate", board.getGateList());
//		return result;
//	}

	@PostMapping(value = "/board/upload/{uploadFolder}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> imageUpload(HttpServletRequest request,
            HttpServletResponse response,
            MultipartHttpServletRequest multiFile,
            @RequestParam MultipartFile upload, 
            @PathVariable String uploadFolder) throws Exception{
        // ���� ���� ����
        UUID uid = UUID.randomUUID();
        
        OutputStream out = null;
        
        //���ڵ�
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        Map<String, String> result = new HashMap<>();
        
        try{
            
            //���� �̸� ��������
            //String fileName = upload.getOriginalFilename();
            String fileName = upload.getOriginalFilename();
            String fileExt = fileName.substring(fileName.lastIndexOf('.') + 1);
            byte[] bytes = upload.getBytes();
            
            //�̹��� ��� ����
            String uploadPath = "resources\\upload\\" + uploadFolder + "\\";
            String path = request.getServletContext().getRealPath("/") + uploadPath;// fileDir�� ���� ������ �׳� �̹��� ��� �������ָ� �ȴ�.
            String fileFullName = uid + "." + fileExt;
            String ckUploadPath = path + fileFullName;
            File folder = new File(path);
            
            log.info(request.getServletContext().getRealPath(ckUploadPath));
            log.info("path : " + path);
                        
            //�ش� ���丮 Ȯ��
            if(!folder.exists()){
                try{
                    folder.mkdirs(); // ���� ����
                    log.info("����");
                }catch(Exception e){
                    e.getStackTrace();
                }
            }
            
            out = new FileOutputStream(new File(ckUploadPath));
            out.write(bytes);
            out.flush(); // outputStram�� ����� �����͸� �����ϰ� �ʱ�ȭ
            
            String domain = request.getRequestURL().toString().replace(request.getRequestURI().toString(), "");
            String fileUrl = domain + "/farmocean/resources/upload/cs_img/" + fileFullName;
            
            log.info("fileUrl : " + fileUrl);
                       
            result.put("filename", fileName);
            result.put("uploaded", "1");
            result.put("url", fileUrl);
            
            if(out != null) { out.close(); }
            
        }catch(IOException e){
            e.printStackTrace();
            result.put("filename", "");
            result.put("uploaded", "0");
            result.put("url", "");            
        }
        
        log.info(request.getRequestURI());
        log.info(request.getRequestURL());
        
        return result;
    }

}

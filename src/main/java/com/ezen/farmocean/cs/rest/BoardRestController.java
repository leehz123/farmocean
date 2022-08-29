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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezen.farmocean.cs.dto.BoardCate;
import com.ezen.farmocean.cs.dto.CsBoard;
import com.ezen.farmocean.cs.service.BoardService;
import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.cs.service.WebGetService;
import com.ezen.farmocean.member.dto.LoginMember;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class BoardRestController {
	
	@Autowired
	BoardService board;	
	
	@Autowired
	CommonFunction cf;
	
	@Autowired
	HttpServletRequest req;
	
	/**
	 * 로그인 정보 임시 추가
	 * @param req
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@GetMapping(value = "/board/temp_login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public LoginMember TempLogin() {
		
		LoginMember member = new LoginMember();
		
		HttpSession session = req.getSession();
		
		member.setMember_id("softdol");
		member.setMember_name("박민호");
		member.setMember_nickName("소프트");
		member.setMember_pw("");
		member.setMember_type("B");
		
		session.setAttribute("loginId", member);
		
		return (LoginMember)session.getAttribute("loginId");
				
	}
	
	/**
	 * 로그 아웃
	 * @return
	 */
	@GetMapping(value = "/board/temp_logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public LoginMember TempLogout() {
		
		HttpSession session = req.getSession();
		session.removeAttribute("loginId");
		
		return (LoginMember)session.getAttribute("loginId");
	}
	
	
	/**
	 * 로그인 정보 확인 rest
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/board/temp_login_info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public LoginMember boardTempLoginInfo() {
		
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

	/**
	 * 공지사항 파일 업로드
	 * @param response
	 * @param multiFile
	 * @param upload
	 * @param uploadFolder
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/board/upload/{uploadFolder}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> imageUpload(HttpServletResponse response,
            MultipartHttpServletRequest multiFile,
            @RequestParam MultipartFile upload, 
            @PathVariable String uploadFolder) throws Exception{
		
		Map<String, String> result = new HashMap<>();
		
		LoginMember mInfo = cf.loginInfo(req);
		
		if(mInfo.getMember_id() == null) {
			result.put("filename", "");
            result.put("uploaded", "0");
            result.put("url", "");      
			
			return result;
		}
		
        // 랜덤 문자 생성		
        UUID uid = UUID.randomUUID();
        
        OutputStream out = null;
        
        //인코딩
        req.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        
        try{
            
            //파일 이름 가져오기
            //String fileName = upload.getOriginalFilename();
            String fileName = upload.getOriginalFilename();
            String fileExt = fileName.substring(fileName.lastIndexOf('.') + 1);
            byte[] bytes = upload.getBytes();
            
            //이미지 경로 생성
            String uploadPath = "resources\\upload\\" + uploadFolder + "\\";
            String path = req.getServletContext().getRealPath("/") + uploadPath;// fileDir는 전역 변수라 그냥 이미지 경로 설정해주면 된다.
            String fileFullName = uid + "." + fileExt;
            String ckUploadPath = path + fileFullName;
            File folder = new File(path);
            
            log.info(req.getServletContext().getRealPath(ckUploadPath));
            log.info("path : " + path);
                        
            //해당 디렉토리 확인
            if(!folder.exists()){
                try{
                    folder.mkdirs(); // 폴더 생성
                    log.info("생성");
                }catch(Exception e){
                    e.getStackTrace();
                }
            }
            
            out = new FileOutputStream(new File(ckUploadPath));
            out.write(bytes);
            out.flush(); // outputStram에 저장된 데이터를 전송하고 초기화
            
            String domain = req.getRequestURL().toString().replace(req.getRequestURI().toString(), "");
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
        
        log.info(req.getRequestURI());
        log.info(req.getRequestURL());
        
        return result;
    }
	
	/**
	 * 가져온 공지사항 등록
	 * @param pInfo
	 * @return
	 */
	@PostMapping(value = "/board/notice_insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> insBoardNotice(@RequestBody Map<String,String> pInfo){
		
		Map<String, String> result = new HashMap<>();
		
		LoginMember mInfo = cf.loginInfo(req);
		
		if(mInfo.getMember_id() == null) {			  
			result.put("code", "0");
			return result;
		}
				
		try {
		
			CsBoard csBoard = new CsBoard();
			
			csBoard.setBoard_cate(3);
			csBoard.setBoard_header(0);
			csBoard.setBoard_title(pInfo.get("title"));
			csBoard.setBoard_memo(pInfo.get("memo"));
			csBoard.setBoard_writer(mInfo.getMember_id());
			
			log.info(csBoard);
			
			board.setBoardIns(csBoard);
			result.put("code", "1");
		}catch (Exception e) {
			e.printStackTrace();
			result.put("code", "0");
		}
		
		return result;
		
	}
	
	/**
	 * 글 가져오기 내용 확인(농수산물 거래)
	 * @param getUrl
	 * @return
	 */
	@PostMapping(value = "/board/notice_conf", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> confBoardNotice(@RequestBody Map<String,String> getUrl){
		
		log.info(getUrl);
		
		Map<String, String> result = new HashMap<>();
		
		if(getUrl.get("getUrl") != null) {		
			WebGetService webService = new WebGetService(getUrl.get("getUrl"));
			result = webService.getpInfo();
		}
		
		result.put("code", "3");
		
		return result;
	}
	
	/**
	 * 글 삭제
	 * @param getUrl
	 * @return
	 */
	@PostMapping(value = "/board/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> delBoard(@RequestBody Map<String,String> getUrl){
		
		LoginMember mInfo = cf.loginInfo(req);
		
		Map<String, String> result = new HashMap<>();
		
		if(mInfo.getMember_id() == null) {
			result.put("code", "-1");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			
			return result;
		}
		
		Integer idx = Integer.parseInt(getUrl.get("num"));
		
		if(!mInfo.getMember_id().equals(board.getBoardWriter(idx))) {
			result.put("code", "-2");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
			return result;
		}
//		
//		if(getUrl.get("getUrl") != null) {		
//			WebGetService webService = new WebGetService(getUrl.get("getUrl"));
//			result = webService.getpInfo();
//		}
		
		if(board.setBoardDelete(idx) > 0) {
			result.put("code", "1");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
		}else {
			result.put("code", "-4");
			result.put("msg", cf.getErrMessage(Integer.parseInt(result.get("code"))));
		}		
		
		return result;
	}
}

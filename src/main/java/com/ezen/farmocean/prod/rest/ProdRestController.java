package com.ezen.farmocean.prod.rest;


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

import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.prod.dto.JoinReviewMember;
import com.ezen.farmocean.prod.dto.Product;
import com.ezen.farmocean.prod.dto.ProductComment;
import com.ezen.farmocean.prod.mapper.JoinReviewMemberMapper;
import com.ezen.farmocean.prod.service.EtcServiceImpl;
import com.ezen.farmocean.prod.service.ProdCommentServiceImpl;
import com.ezen.farmocean.prod.service.ProdReviewServiceImpl;
import com.ezen.farmocean.prod.service.ProdServiceImpl;

import lombok.extern.log4j.Log4j2;

@Log4j2
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
	
	@Autowired
	HttpServletRequest req;
	
	@Autowired
	HttpSession session;
	
	//임시로긴
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

		//로그아웃
	   @GetMapping(value = "/prod/temp_logout", produces = MediaType.APPLICATION_JSON_VALUE)
	   public LoginMember tempLogout(HttpServletRequest req) {
	      
	      HttpSession session = req.getSession();
	      session.removeAttribute("loginId");
	      
	      return (LoginMember)session.getAttribute("loginId");
	   }



	   @GetMapping(value="/prod/select_prod_review/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public List<JoinReviewMember> selectReview(@PathVariable("prod_idx") Integer prod_idx) {
		   return jrm.getReviewMemberListByProdIdx(prod_idx);
	   }
	
	   
	   
	   @GetMapping(value="/prod/get_login_id", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public String authentication() { 
		   LoginMember member = (LoginMember) session.getAttribute("loginId");		   
		   String id = null;
		   
		   try {
			   id = member.getMember_id();
		   } catch(Exception e) {
			   System.out.println(e.getMessage());
		   }
		   return id; 
	   }
	   

	   @GetMapping(value="/authentication_seller/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Integer sellerAuthentication(@PathVariable Integer prod_idx) { 
		   
		   String id = null;
		   LoginMember member = (LoginMember) session.getAttribute("loginId");		   
		   
		   if(member == null) {
			   return 0;
		   }
		    
		   try {
			   id = member.getMember_id();
		   } catch(Exception e) {
			   System.out.println(e.getMessage());
		   }
		   
		   Product product = prod.getProductById(prod_idx);
		   String seller = product.getMember_id();
		   
		   if(!id.equals(seller)) {
			   return 0;
		   } 		   
		   return 1; 
	   }

	   
	   
	   @GetMapping(value="/prod/get_product/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Product getproductByprodIdx(@PathVariable Integer prod_idx) {
		   Product product = prod.getProductById(prod_idx);
		   return product;
	   }

	   
	   @PostMapping(value="/prod_detail_write_img_upload/{uploadFolder}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Map<String, String> insertProduct(HttpServletRequest req,HttpServletResponse resp, MultipartHttpServletRequest multiFile, CommonFunction cf,
	            					@RequestParam MultipartFile upload, @PathVariable String uploadFolder) throws Exception {
		   
		   	//결과 받은 Map<String, String>
			Map<String, String> result = new HashMap<>();
			
			//로그인 중인 계정 정보 얻기
			LoginMember mInfo = cf.loginInfo(req);
			
			//로그인 중인 계정이 없을 때 안전장치
			if(mInfo.getMember_id() == null) {
				result.put("filename", "");
	            result.put("uploaded", "0");
	            result.put("url", "");      
				
				return result;
			}
			
	        // 랜덤 문자 생성		
	        UUID uid = UUID.randomUUID();
	        
	        //아풋스 준비
	        OutputStream out = null;
	        
	        //인코딩
	        req.setCharacterEncoding("UTF-8");
	        resp.setCharacterEncoding("UTF-8");
	        resp.setContentType("text/html;charset=UTF-8");

	        
	        try{
	            
	            String fileName = upload.getOriginalFilename(); //확장자 없는 순수(?)파일명
	            String fileExt = fileName.substring(fileName.lastIndexOf('.') + 1); // 확장자
	            byte[] bytes = upload.getBytes(); // 업로드된 파일을 byte로 변환
	            
	            //이미지 경로 생성
	            String uploadPath = "resources\\upload\\" + uploadFolder + "\\"; //저장될 폴더 경로
	            String path = req.getServletContext().getRealPath("/") + uploadPath; //앞에 farmocean/붙인 폴더경로	// fileDir는 전역 변수라 그냥 이미지 경로 설정해주면 된다.
	            String fileFullName = uid + "." + fileExt; // 최종 파일명 = uuid+파일명+확장자
	            String ckUploadPath = path + fileFullName; // 저장될 폴더 경로 + 최종 파일명
	            File folder = new File(path); // 저장될 폴더 생성할 준비
	            
//	            log.info(req.getServletContext().getRealPath(ckUploadPath));
//	            log.info("path : " + path);
	                        
	            //해당 디렉토리 확인
	            if(!folder.exists()){
	                try{
	                    folder.mkdirs(); // 저장될 폴더 생성
	                    log.info("생성");
	                }catch(Exception e){
	                    e.getStackTrace();
	                }
	            }
	            
	            //생성된 폴더에 파일 생성(파일 내보내기)
	            out = new FileOutputStream(new File(ckUploadPath)); 
	            out.write(bytes);//생성된 파일에 데이터 채우기
	            out.flush(); // outputStram에 저장된 데이터를 전송하고 초기화
	            
	            
	            
	            
	            String domain = req.getRequestURL().toString().replace(req.getRequestURI().toString(), ""); //요청보낸 http://localhost:8888/farmocean/prod/insert_product/{uploadFolder} 에서 http://localhost:8888만 남기기  
	            String fileUrl = domain + "/farmocean/resources/upload/prod_img/" + fileFullName; // http://localhost:8888/farmocean/resources/upload/prod_img/최종파일명
	            
	            //log.info("fileUrl : " + fileUrl);
	            
	            //map에 순수파일명(확장자x), 업로르됨 1, 파일 URL 저장 
	            result.put("filename", fileName);
	            result.put("uploaded", "1");
	            result.put("url", fileUrl);
	            
	            
	            //out작업이 정상 종료 됐을 때 out.close()
	            if(out != null) { out.close(); }
	            
	        }catch(IOException e){
	            e.printStackTrace();
	            result.put("filename", "");
	            result.put("uploaded", "0");
	            result.put("url", "");            
	        }
	        
//	        log.info(req.getRequestURI());
//	        log.info(req.getRequestURL());
	        
	        return result;
	   }

	   

	   

	   public List<ProductComment> getCommentList(Integer prod_idx) {
			  
		   LoginMember member = (LoginMember) session.getAttribute("loginId");
		   String member_id = null;
		   List<ProductComment> commentList = null;
		   
		   try {
			   member_id = member.getMember_id();
		   } catch(Exception e) {
			   System.out.println(e.getMessage());
		   }
//		   System.out.println("접속 중인 아이디(상상페) : " + member_id);
		   
		   if(member_id != null) {
			   comment.updateUserCommentAccessible(prod_idx, member_id);
		   } else {
			   comment.updateNonUserCommentAccessible(prod_idx);
		   }
		   
		   try {
			   commentList = comment.getCommentListByProdIdx(prod_idx);
		   } catch(Exception e) {
			   System.out.println(e.getMessage());
		   }

		   return commentList;
	   }
	   
	   
	   
	   @PostMapping(value = "/prod/insert_comment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Integer insertReview(@RequestBody ProductComment productComment) {

		   List<ProductComment> commentList = null; 
		   LoginMember member = (LoginMember)session.getAttribute("loginId");
		   String Login_member_id = member.getMember_id();

		   Integer prod_idx = productComment.getProd_idx();
		   String commentContent = productComment.getComment_content();
		   Integer commentSecret = productComment.getComment_secret();
		   Integer commentAccessible = 1;
		   return comment.insertComment(prod_idx, Login_member_id, commentContent, commentSecret, commentAccessible);
		   
//		   try {
//			   commentList = getCommentList(prod_idx);
//		   } catch(Exception e) {
//			   System.out.println(e.getMessage());
//			   return null;
//		   }
//		   return commentList; 
	   }
	   
	   //아 이건 또 UTF-8로 보내야 안 깨지네 (jsp, js 다 EUC-KR로 맞춘 상태)
	   @GetMapping(value="/prod/select_prod_comment/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public List<ProductComment> selectComment(@PathVariable("prod_idx") Integer prod_idx) {		   		   
		   return getCommentList(prod_idx);
	   }
	   

	   
	   @GetMapping(value="/prod/get_member_image/{member_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public String getMemberProfile(@PathVariable("member_id") String member_id) {
//		   System.out.println("일단 레컨 도착");
//		   System.out.println("여기까지는? : " + etc.getMemberImageById(member_id));
		   return etc.getMemberImageById(member_id);
	   }

	   @GetMapping(value = "/prod/delete_comment/{prod_idx}/{comment_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Integer deleteComment(@PathVariable("prod_idx") Integer prod_idx, @PathVariable("comment_idx") Integer comment_idx) {
//		   System.out.println(comment_idx + "/" + prod_idx);
		   return comment.deleteComment(comment_idx);
		   //return getCommentList(prod_idx);
	   }
	   
	   @PostMapping(value = "/prod/update_comment_reply", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Integer updateCommentReply(@RequestBody ProductComment productComment) {	   
		   
		   Integer commentIdx = productComment.getComment_idx();
		   String commentReply = productComment.getComment_reply();
		   System.out.println(commentIdx + "/" + commentReply);
		   
		   return comment.updateCommentReply(commentIdx, commentReply);
	   }
	   
	   
	   
	   
	   
	   
}


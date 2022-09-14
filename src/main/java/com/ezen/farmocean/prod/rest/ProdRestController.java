package com.ezen.farmocean.prod.rest;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.service.MemberService;
import com.ezen.farmocean.prod.dto.JoinReviewMember;
import com.ezen.farmocean.prod.dto.Product;
import com.ezen.farmocean.prod.dto.ProductComment;
import com.ezen.farmocean.prod.dto.ReviewPicture;
import com.ezen.farmocean.prod.mapper.JoinReviewMemberMapper;
import com.ezen.farmocean.prod.service.EtcServiceImpl;
import com.ezen.farmocean.prod.service.ProdCommentServiceImpl;
import com.ezen.farmocean.prod.service.ProdReviewServiceImpl;
import com.ezen.farmocean.prod.service.ProdServiceImpl;
import com.ezen.farmocean.prod.service.ReviewPictureServiceImpl;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class ProdRestController {

	//�ڡڡڡ� JS�� JSON������ ���� �� produces = MediaType.APPLICATION_JSON_UTF8_VALUE�� ������ �� ����! (jsp, js �� EUC-KR�� ���� ���¿���)
	
	@Autowired
	MemberService member;
	
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
	
	@Autowired
	ReviewPictureServiceImpl rp;
	
	//�ӽ÷α�
	@GetMapping(value="/prod/temp_login", produces = MediaType.APPLICATION_JSON_VALUE)
	public LoginMember tempLogin(HttpServletRequest req){
		LoginMember member = new LoginMember();
	
		HttpSession session = req.getSession();
	      member.setMember_id("kingbambbang");
	      member.setMember_name("��ȸ��");
	      member.setMember_nickName("�չ㻧");
	      member.setMember_pw("asdf1234");
	      member.setMember_type("B");
	      
	      session.setAttribute("loginId", member);
	      
	      return (LoginMember)session.getAttribute("loginId");
	}

		//�α׾ƿ�
	   @GetMapping(value = "/prod/temp_logout", produces = MediaType.APPLICATION_JSON_VALUE)
	   public LoginMember tempLogout(HttpServletRequest req) {
	      
	      HttpSession session = req.getSession();
	      session.removeAttribute("loginId");
	      
	      return (LoginMember)session.getAttribute("loginId");
	   }

	   
	   //���� �α��� ���� ���̵� ��������
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


 //___________________________________________________________��ǰ__________________________________________________________	   
	   
	   // ���� �α��� ���� ���̵� prod_idx�� �ش��ϴ� ��ǰ �Ǹ��ڿ� ��ġ�ϸ� 1 ��ȯ
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

	   
	   
	   // ��ǰ ���̵�� ��ǰ ��������
	   @GetMapping(value="/prod/get_product/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Product getproductByprodIdx(@PathVariable Integer prod_idx) {
		   Product product = prod.getProductById(prod_idx);
		   return product;
	   }
	   
	   
//CKEDITOR__________________________________________________
	   
	   // ��ǰ �󼼱� �ۼ� ���������� CK Editor �� �̹��� ������ ���ε��ϱ� 
	   @PostMapping(value="/prod_detail_write_img_upload/{uploadFolder}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Map<String, String> CKUploadProductImg(HttpServletRequest req,HttpServletResponse resp, CommonFunction cf,
	            					@RequestParam MultipartFile upload, @PathVariable String uploadFolder) throws Exception {
		   
		   	//��� ���� Map<String, String>
			Map<String, String> result = new HashMap<>();
			
			//�α��� ���� ���� ���� ���
			LoginMember mInfo = cf.loginInfo(req);
			
			//�α��� ���� ������ ���� �� ������ġ
			if(mInfo.getMember_id() == null) {
				result.put("filename", "");
	            result.put("uploaded", "0");
	            result.put("url", "");      
				
				return result;
			}
			
	        // ���� ���� ����		
	        UUID uid = UUID.randomUUID();
	        
	        //��ǲ�� �غ�
	        OutputStream out = null;
	        
	        //���ڵ�
	        req.setCharacterEncoding("UTF-8");
	        resp.setCharacterEncoding("UTF-8");
	        resp.setContentType("text/html;charset=UTF-8");

	        
	        try{
	            
	            String fileName = upload.getOriginalFilename(); //Ȯ���� ���� ����(?)���ϸ�
	            String fileExt = fileName.substring(fileName.lastIndexOf('.') + 1); // Ȯ����
	            byte[] bytes = upload.getBytes(); // ���ε�� ������ byte�� ��ȯ
	            
	            //�̹��� ��� ����
	            String uploadPath = "resources\\upload\\" + uploadFolder + "\\"; //����� ���� ���

	            String path = req.getServletContext().getRealPath("/") + uploadPath; // fileDir�� ���� ������ �׳� �̹��� ��� �������ָ� �ȴ�.
	           //������ ������ ���丮�� ������ ���(C:\\JavaAWS\\spring-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\project-farmocean\\) + resources\\upload\\���ε��� ������\\ 
	            
	            String fileFullName = uid + "." + fileExt; // ���� ���ϸ� = uuid+���ϸ�+Ȯ����
	            
	            String ckUploadPath = path + fileFullName; // ����� ���� ��� + ���� ���ϸ�
	            File folder = new File(path); // ����� ���� ������ �غ�
	            
//	            log.info(req.getServletContext().getRealPath(ckUploadPath));
//	            log.info("path : " + path);
	                        
	            //�ش� ���丮 Ȯ��
	            if(!folder.exists()){
	                try{
	                    folder.mkdirs(); // ����� ���� ����
	                    log.info("����");
	                }catch(Exception e){
	                    e.getStackTrace();
	                }
	            }
	            
	            //������ ������ ���� ����(���� ��������)
	            out = new FileOutputStream(new File(ckUploadPath)); 
	            out.write(bytes);//������ ���Ͽ� ������ ä���
	            out.flush(); // outputStram�� ����� �����͸� �����ϰ� �ʱ�ȭ
	            
	            
	            
	            
	            String domain = req.getRequestURL().toString().replace(req.getRequestURI().toString(), ""); //��û���� http://localhost:8888/farmocean/prod/insert_product/{uploadFolder} ���� http://localhost:8888�� �����  
	            String fileUrl = domain + "/farmocean/resources/upload/prod_img/" + fileFullName; // http://localhost:8888/farmocean/resources/upload/prod_img/�������ϸ�
	            
	            //log.info("fileUrl : " + fileUrl);
	            
	            //map�� �������ϸ�(Ȯ����x), ���θ��� 1, ���� URL ���� 
	            result.put("filename", fileName);
	            result.put("uploaded", "1");
	            result.put("url", fileUrl);
	            
	            
	            //out�۾��� ���� ���� ���� �� out.close()
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

	   
	 //___________________________________________________________�ı�__________________________________________________________
	   
	   // ��ǰ ���̵�� ��ǰ ��ǰ ���� ��� �������� 
	   @GetMapping(value="/prod/select_prod_review/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public List<JoinReviewMember> selectReview(@PathVariable("prod_idx") Integer prod_idx) {  
		   return jrm.getReviewMemberListByProdIdx(prod_idx);
	   }

	   //����Ƶ𿢽��� �ش��ϴ� ������ó��� ���
	   @GetMapping(value="/prod/select_review_picture_list/{review_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public List<ReviewPicture> getReviewPictureByReviewIdx(@PathVariable("review_idx") Integer review_idx) {
		   return rp.getReviewPicturebyReviewIdx(review_idx); 
	   }

	   
	   
	   // member_id�� ������ �̹��� ��������
	   @GetMapping(value="/prod/get_member_image/{member_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public String getMemberProfile(@PathVariable("member_id") String member_id) {
//		   System.out.println("�ϴ� ���� ����");
//		   System.out.println("���������? : " + etc.getMemberImageById(member_id));
		   return etc.getMemberImageById(member_id);
	   }

	   
	   
	   // ��� ���̵�� ��� �г��� ��������(�̰� �ı� ���� ���� ���̰�, ��ǰ ����Ʈ ��� ���� ����) 
	   @GetMapping(value = "/prod/get_member_nickname_by_member_id/{member_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public String getMemberNickNameByMemberId(@PathVariable String member_id) {   
		   String nickname = member.getMember(member_id).getMember_nickName();		   
		   return nickname;  
	   }

	   
		//�̰� ȣ���ϴ� ���� �˾�â�̴ϱ� �۾� �Ϸ��ϰ� �ٸ� �������� �Ѿ �ʿ� ���� �׳� ��� ���� ���� â �ݰ� ���� �����ϸ� ������? �״ϱ� �������� ó������
		@PostMapping(value="/prod/insert_review", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, String> insert_review(  
									@RequestBody Map<String, Object> param
									//ProductReview productReview, 
									//@RequestParam(value = "file_paths", required=false) List<String> filePaths
									) {

			System.out.println((String)param.get("member_id"));
			System.out.println((String)param.get("member_nickname"));
			System.out.println((String)param.get("prod_idx"));
			System.out.println((String)param.get("file_paths"));
			System.out.println((String)param.get("review_content"));
			System.out.println((String)param.get("review_starnum"));
			
			Map<String, String> resultMap = new HashMap<>();
			String member_id = (String)param.get("member_id");
			//String member_nickname = (String)param.get("member_nickname");
			Integer prod_idx = Integer.parseInt((String)param.get("prod_idx"));
			String file_paths = (String)param.get("file_paths");
			String review_content = (String)param.get("review_content");
			Integer review_starnum = Integer.parseInt((String)param.get("review_starnum"));
			
			
		   Long datetime = System.currentTimeMillis();
	       Timestamp timestamp = new Timestamp(datetime);
		
			try {
				review.insertReviewWithJavaTS(prod_idx, member_id, review_content, timestamp, review_starnum); 
				
				Integer review_idx = review.getReviewIdxByIdAndDate(member_id, timestamp);
			
				String[] filePathsArr = file_paths.split("#");
				
				for(String filePath : filePathsArr) {
					rp.insertReviewPicture(review_idx, filePath);				
				}
				
				resultMap.put("result", "OK");
			
			} catch(Exception e) {
				System.out.println(e.getMessage());
				resultMap.put("result", "FAIL");
				return resultMap;
			}

			return resultMap;
		}
		
		
		
		//�ı� ���� ���ε� (multipart/form-data �ѱ� ���� ���� ������ �ᱹ ���� ���ε常 ���� ��. ���ϸ� ������ ������ ���ε��� �� ���� ���ϸ��� ������ �� ���� ��)
		@PostMapping(value="/prod/upload_image", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, List<String>> uploadImage(	HttpServletRequest request,
									@RequestParam("attach_file") List<MultipartFile> multipartFile) throws UnsupportedEncodingException {
			
			Map<String, List<String>> resultMap = new HashMap<>();
			
			List<String> targetFilesNames = new ArrayList<>();   
			
			String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
			String fileRoot;
			try {
				// ������ ������ ź��.
				if(multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
					
					for(MultipartFile file:multipartFile) {
						fileRoot = contextRoot + "resources/upload/prod_review_img/";
						//System.out.println(fileRoot); //C:\JavaAWS\spring-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\project-farmocean\resources/upload/prod_review_img/
						
						String originalFileName = file.getOriginalFilename();	//�������� ���ϸ�
						String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//���� Ȯ����
						String savedFileName = UUID.randomUUID() + extension;	//����� ���� ��
						
						String targetFileStr = fileRoot + savedFileName;
						File targetFile = new File(fileRoot + savedFileName);	
						try {
							InputStream fileStream = file.getInputStream();
							FileUtils.copyInputStreamToFile(fileStream, targetFile); //���� ����
							targetFilesNames.add(targetFileStr);							
						} catch (Exception e) {
							//���ϻ���
							FileUtils.deleteQuietly(targetFile);	//����� ���� ���� ����
							e.printStackTrace();
							break;
						}
					}
					resultMap.put("result", targetFilesNames);
				}
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
			return resultMap;			
		}

	   
		//�ı���� ����
		@GetMapping(value="/prod/delete_image/{file_paths}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, String> deleteImgFile(@RequestParam("attach_file") List<MultipartFile> multipartFile) {
			
			Map<String, List<String>> resultMap = new HashMap<>();
			
			File file = new File("C:/123.txt");
	        
	    	if( file.exists() ){
	    		if(file.delete()){
	    			System.out.println("���ϻ��� ����");
	    		}else{
	    			System.out.println("���ϻ��� ����");
	    		}
	    	}else{
	    		System.out.println("������ �������� �ʽ��ϴ�.");
	    	}
			
			return null;
		}
		
	   
//___________________________________________________________���__________________________________________________________	   
	
	   // prod_idx�� �ش��ϴ� ��� ����Ʈ ��������. �ؿ� �Ŷ� �ߺ� �ƴѰ� �ٵ� ��� �� ���� ������ �𸣴� �ϴ� ŵ
	   @GetMapping(value="/prod/select_prod_comment/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public List<ProductComment> selectComment(@PathVariable("prod_idx") Integer prod_idx) {		   		   
		   return getCommentList(prod_idx);
	   }


	   
	   // prod_idx�� �ش��ϴ� ��� ����Ʈ �������� (�׳� �Ϲ� �޼���)
	   public List<ProductComment> getCommentList(Integer prod_idx) {
			  
		   LoginMember member = (LoginMember) session.getAttribute("loginId");
		   String member_id = null;
		   List<ProductComment> commentList = null;
		   
		   try {
			   member_id = member.getMember_id();
		   } catch(Exception e) {
			   System.out.println(e.getMessage());
		   }
//		   System.out.println("���� ���� ���̵�(�����) : " + member_id);
		   
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

	   
	   // ��� ���
	   @PostMapping(value = "/prod/insert_comment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Integer insertReview(@RequestBody ProductComment productComment) {

		   List<ProductComment> commentList = null; 
		   LoginMember member = (LoginMember)session.getAttribute("loginId");
		   if(member== null) {
			   return 2; //���� �α��� ���� ���� ����
		   }
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
	   
	   
	   
	   // ��ǰ ��� ����
	   @GetMapping(value = "/prod/delete_comment/{prod_idx}/{comment_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Integer deleteComment(@PathVariable("prod_idx") Integer prod_idx, @PathVariable("comment_idx") Integer comment_idx) {
//		   System.out.println(comment_idx + "/" + prod_idx);
		   return comment.deleteComment(comment_idx);
		   //return getCommentList(prod_idx);
	   }
	   
	   
	   // ��ǰ ��ۿ� ��� ���
	   @PostMapping(value = "/prod/update_comment_reply", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Integer updateCommentReply(@RequestBody ProductComment productComment) {	   
		   
		   Integer commentIdx = productComment.getComment_idx();
		   String commentReply = productComment.getComment_reply();
		   System.out.println(commentIdx + "/" + commentReply);
		   
		   return comment.updateCommentReply(commentIdx, commentReply);
	   }
	
	   

	   
   
}


package com.ezen.farmocean.prod.rest;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ctc.wstx.dtd.FullDTDReader;
import com.ezen.farmocean.admin.dto.BuyInfo;
import com.ezen.farmocean.admin.service.JsonProdServiceImpl;
import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.follow.dto.Follow;
import com.ezen.farmocean.follow.service.FollowService;
import com.ezen.farmocean.follow.service.FollowServiceImpl;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.service.MemberService;
import com.ezen.farmocean.prod.dto.JoinReviewMember;
import com.ezen.farmocean.prod.dto.ProdImg;
import com.ezen.farmocean.prod.dto.Product;
import com.ezen.farmocean.prod.dto.ProductComment;
import com.ezen.farmocean.prod.dto.ReviewPicture;
import com.ezen.farmocean.prod.mapper.JoinReviewMemberMapper;
import com.ezen.farmocean.prod.service.EtcServiceImpl;
import com.ezen.farmocean.prod.service.ProdCommentServiceImpl;
import com.ezen.farmocean.prod.service.ProdImgServiceImpl;
import com.ezen.farmocean.prod.service.ProdReviewServiceImpl;
import com.ezen.farmocean.prod.service.ProdServiceImpl;
import com.ezen.farmocean.prod.service.ReviewPictureServiceImpl;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class ProdRestController {

	//★★★★ JS에 JSON데이터 보낼 때 produces = MediaType.APPLICATION_JSON_UTF8_VALUE로 보내야 안 깨짐! (jsp, js 다 EUC-KR로 맞춘 상태여도)
	
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
	
	
   @Autowired
   ProdImgServiceImpl prodImgService;

	   
	   
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

	   
	   //현재 로그인 중인 아이디 가져오기
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


 //___________________________________________________________상품__________________________________________________________	   

		
	   
		//상품 사진 업로드 (multipart/form-data 한글 깨짐 오류 때문에 결국 파일 업로드만 따로 함. 파일명도 깨져서 서버에 업로드할 때 원본 파일명은 유지할 수 없을 듯)
		@PostMapping(value="/prod/upload_prod_image", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, List<String>> uploadProdImage(HttpServletRequest request,
									@RequestParam("attach_file") List<MultipartFile> multipartFile) throws UnsupportedEncodingException {
			
			Map<String, List<String>> resultMap = new HashMap<>();
			
			List<String> targetFilesNames = new ArrayList<>();   
			
			String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
			String fileRoot;
			try {
				// 파일이 있을때 탄다.
				if(multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
					
					for(MultipartFile file:multipartFile) {
						fileRoot = contextRoot + "resources/upload/prod_detail_img/"; //C:\JavaAWS\spring-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\project-farmocean\resources/upload/prod_review_img/
						
						String originalFileName = file.getOriginalFilename();	//오리지날 파일명
						String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
						String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
						
						
						File targetFile = new File(fileRoot + savedFileName);	
						try {
							InputStream fileStream = file.getInputStream();
							FileUtils.copyInputStreamToFile(fileStream, targetFile); //파일 저장
							targetFilesNames.add("/resources/upload/prod_detail_img/" + targetFile.getName());	
							
						} catch (Exception e) {
							//파일삭제
							FileUtils.deleteQuietly(targetFile);	//저장된 현재 파일 삭제
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

	   
	   
	   
	   	//http://localhost:8888/farmocean/product/insert_prod
		@RequestMapping(value = "/insert_prod", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, Integer> insert_prod (@RequestBody Map<String, Object> param) throws ParseException {

			Map<String, Integer> map = new HashMap<>(); 
			System.out.println("파일패스들 : " + req.getParameter("file-paths"));			
			
			String file_paths = (String)param.get("file_paths");
	
	        String inDateObj = (String)param.get("prod_sell_deadline");
	        String inDate = inDateObj.replace('T', ' ');
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        Date date = df.parse(inDate);
	        long time = date.getTime();
	        Timestamp prod_sell_deadline = new Timestamp(time);
	        
	        LoginMember member = (LoginMember) session.getAttribute("loginId");
	        String member_id = member.getMember_id();
	        String prod_name = (String)param.get("prod_name");

	        String prod_info = null;
	        String inputContent = (String)param.get("prod_info");
	        if(inputContent != null) {
	        	inputContent.replace("<p>", "");
	            inputContent.replace("</p>", "");
	            inputContent.replace("&nbsp;", "");
	        }

	        
	        if(inputContent.length() < 1 || inputContent == null || inputContent.equals("")) {
	        	prod_info = "<p>상품 상세내용 준비 중입니다.</p>";
	        } else {
	        	prod_info = (String)param.get("prod_info");
	        }
	        
	        Integer prod_price = Integer.parseInt((String)param.get("prod_price"));
	        Integer prod_stock = Integer.parseInt((String)param.get("prod_stock"));
	        Integer cate_idx = Integer.parseInt((String)param.get("cate_idx"));
	        
	        //작성일 타임스탬프 구하기
	        Date today = new Date();
	        long todayTime = today.getTime();
	        Timestamp prod_written_date = new Timestamp(todayTime);
	        
	 

	        try {
	        	prod.insertProduct(member_id, prod_name, prod_info, cate_idx, "판매중", prod_price, prod_sell_deadline, prod_stock, 0, 0, prod_written_date);
	        	//pService.addProduct(member_id, prod_name, prod_info, cate_idx, prod_price, prod_sell_deadline, prod_stock, 0, 0, prod_written_date);
	        	
	        	Integer prod_idx = prod.getProdIdxByIdAndDate(member_id, prod_written_date); 
	            map.put("prod_idx", prod_idx);
	            
				String[] filePathsArr = file_paths.split("#");
				for(int i = 0; i < filePathsArr.length; ++i) {
					if(i == 0) {
						prodImgService.insertProdImg(prod_idx, filePathsArr[i], 1); //사진패스 여기로 올 때 맨 처음 이미지패스가 대표이미지로 올 것
					} else {
						prodImgService.insertProdImg(prod_idx, filePathsArr[i], 0);
					}
				}
				map.put("result", 1); //상품 추가 성공
	        } catch (Exception e) {
	        	log.info(e.getMessage());
	        	map.put("result", -1); //상품 추가 실패
	        	return map;
	        }
	        
			return map;
		}	

		
		
		
		//http://localhost:8888/farmocean/update_prod
		@RequestMapping(value = "/update_prod", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, Integer> update_prod(@RequestBody Map<String, Object> param) throws ParseException {
			
			Map<String, Integer> map = new HashMap<>();

			//String prod_idx_str = req.getRequestURL().toString().replace("/farmocean/product/product_detail_edit/", "");
			String prod_idx_str = (String)param.get("prod_idx"); 
			Integer prod_idx = Integer.parseInt(prod_idx_str);
			String deletedOldImgStr = (String)param.get("deletedOldImgStr");			
			String filePathsStr = (String)param.get("filePathsStr");
			
			//테이블에 있는 url 싹 비우기 
			prodImgService.deleteProdImgByProd_idx(prod_idx);
			
			//이미지 첨부 바뀐게 있으면
			if(!filePathsStr.equals("noImgChange")) {
				//미리보기에서 삭제 된 img_url 파일들 삭제
				try {					
					String[] deletedOldImgsArr = deletedOldImgStr.split("#");
					for(String doi : deletedOldImgsArr) {
						//삭제된 이미지 url DB에서 지우고
						prodImgService.deleteImgByImgURL(doi);
						
						//이미지 파일 삭제 
						String fullPath = req.getServletContext().getRealPath("/") + doi.replace("/", "\\");
			        	File file = new File(fullPath);
			        	
			        	if(file.exists()) {    //삭제하고자 하는 파일이 해당 서버에 존재하면 삭제시킨다
				            file.delete();
				        }
					}
				} catch(Exception e) {
					log.info(e.getMessage());
				}
				
				
				if(filePathsStr.length() != 0) {
					String[] filePathsArr = filePathsStr.split("#");
					
					//filePaths 배열 for 문 돌려서 prod_img테이블에 데이터 저장
					for(int i = 0; i < filePathsArr.length; ++i) {
						int mainImg = 0;
						if(i == 0) {mainImg = 1;}
						prodImgService.insertProdImg(prod_idx, filePathsArr[i], mainImg);				
					}				
				}
			}
			
			
	        String inDateStr = (String)param.get("prod_sell_deadline");
	        String inDate = inDateStr.replace('T', ' ');
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        Date date = df.parse(inDate);
	        long time = date.getTime();
	        Timestamp prod_sell_deadline = new Timestamp(time);
	        	        
	        String prod_name = (String) param.get("prod_name");
	        
	        String prod_info = (String) param.get("prod_info"); 
	        Integer prod_price = Integer.parseInt((String) param.get("prod_price"));
	        Integer prod_stock = Integer.parseInt((String) param.get("prod_stock"));
	        Integer cate_idx = Integer.parseInt((String) param.get("cate_idx"));

	        //작성일 타임스탬프 구하기
	        Date today = new Date();
	        long todayTime = today.getTime();
	        Timestamp prod_written_date = new Timestamp(todayTime);	        
	        
	        try {
	        	prod.updateProduct(prod_idx, prod_name, prod_info, cate_idx, "판매중", prod_price, prod_sell_deadline, prod_stock, 0, prod_written_date);
	        	//pService.editProduct(prod_idx, prod_name, prod_info, cate_idx, prod_price, prod_sell_deadline, prod_stock, 0, prod_written_date);
	        	
	        	map.put("result", 1); //상품 추가 성공
	        	map.put("prod_idx", prod_idx);
	        } catch (Exception e) {
	        	log.info(e.getMessage());
	        	map.put("result", -1); //상품 추가 실패
	        	return map;        	
	        }
	        
	        return map; 
		}

		// 상품 사진, 경로 삭제(파일경로, prod_idx로)
		@RequestMapping(value = "/delete_prod_img_1/{prod_idx}/{file_path}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, Integer> delete_prod(Model model, HttpServletRequest req, @PathVariable("prod_idx") Integer prod_idx, @PathVariable("file_path") String file_path) throws ParseException {
			
			Map<String, Integer> map = new HashMap<>();
			
			try {

				// 이미지 파일 삭제
				List<ProdImg> prodImgList =  prodImgService.getImgsByProdIdx(prod_idx);
		        for(ProdImg img : prodImgList) {
		        	
		        	// ProdImg 테이블 행 삭제
					prodImgService.deleteProdImgByProd_idx(prod_idx);
		        	
		        	
		        	String fullPath = req.getServletContext().getRealPath("/") + img.getImg_url().replace("/", "\\");
		        	System.out.println(fullPath);
		        	File file = new File(fullPath);
		        	
		        	if(file.exists()) {    //삭제하고자 하는 파일이 해당 서버에 존재하면 삭제시킨다
			            file.delete();
			        }
		        }
		        
				
		        
		        
				map.put("result", 1);
				map.put("prod_idx", prod_idx);
				
			} catch(Exception e) {
				log.info(e.getMessage());
				map.put("result", -1);
				return map;
			}
			
			return map;			
		}

		
		
		//상품 삭제 + 상품 이미지 경로 삭제 + 서버에 업로드 된 이미지 삭제
		@RequestMapping(value = "/delete_prod/{prod_idx}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, Integer> delete_prod(Model model, HttpServletRequest req, @PathVariable("prod_idx") Integer prod_idx) throws ParseException {
			
			Map<String, Integer> map = new HashMap<>();
			
			try {
				
				//상품 삭제 상태 2(삭제됨)로 변경
				prod.updateProductStatusDelete(prod_idx);
				
				
				// 여기서부터는 발표 마지막날 풀기 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				
				/*

				// 이미지 파일 삭제
				List<ProdImg> prodImgList =  prodImgService.getImgsByProdIdx(prod_idx);
		        for(ProdImg img : prodImgList) {
		        	
		        	//ProdImg 테이블 행 삭제
					prodImgService.deleteProdImgByProd_idx(prod_idx);
		        	
		        	String fullPath = req.getServletContext().getRealPath("/") + img.getImg_url().replace("/", "\\");
		        	System.out.println(fullPath);
		        	File file = new File(fullPath);
		        	
		        	if(file.exists()) {    //삭제하고자 하는 파일이 해당 서버에 존재하면 삭제시킨다
			            file.delete();
			        }
		        }
				
		        


				 */

				map.put("result", 1);
				
			} catch(Exception e) {
				log.info(e.getMessage());
				map.put("result", -1);
				return map;
			}
			
			return map;			
		}
 
	   
	   
	   // 현재 로그인 중인 아이디가 prod_idx에 해당하는 상품 판매자와 알치하면 1 반환
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

	   
	   
	   // 상품 아이디로 상품 가져오기
	   @GetMapping(value="/prod/get_product/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Product getproductByprodIdx(@PathVariable Integer prod_idx) {
		   Product product = prod.getProductById(prod_idx);
		   return product;
	   }
	   
	   
	   // 상품 아이디로 상품 사진 경로 목록 가져오기
	   @GetMapping(value="/prod/get_product_img/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public List<ProdImg> getproductImgByprodIdx(@PathVariable Integer prod_idx) {
		   List<ProdImg> imgList = prodImgService.getImgsByProdIdx(prod_idx);
		   return imgList;
	   }
	   
	   
	   //판매종료된 상품 prod_sell '판매종료'로 변경
//	   @GetMapping(value="/product/expire_deadline/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	   public Integer expireDeadline(@PathVariable("prod_idx") Integer prod_idx) { 
//		   return prod.expireDeadline(prod_idx);
//	   }
	   
	   
//CKEDITOR__________________________________________________
	   
	   // 상품 상세글 작성 페이지에서 CK Editor 로 이미지 서버에 업로드하기 
	   @PostMapping(value="/prod_detail_write_img_upload/{uploadFolder}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Map<String, String> CKUploadProductImg(HttpServletRequest req,HttpServletResponse resp, CommonFunction cf,
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
	            byte[] bytes = upload.getBytes(); // 업로드될 파일을 byte로 변환
	            
	            //이미지 경로 생성
	            String uploadPath = "resources\\upload\\" + uploadFolder + "\\"; //저장될 폴더 경로

	            String path = req.getServletContext().getRealPath("/") + uploadPath; // fileDir는 전역 변수라 그냥 이미지 경로 설정해주면 된다.
	           //서버의 웹서비스 디렉토리의 물리적 경로(C:\\JavaAWS\\spring-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\project-farmocean\\) + resources\\upload\\업로드할 폴더명\\ 
	            
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
	            
	            //map에 순수파일명(확장자x), 업로드 됨 1, 파일 URL 저장 
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

	   
	 //___________________________________________________________후기__________________________________________________________
	   

	   
	   

		//리뷰 남기기 전에 구매 인증   
		@GetMapping(value="/buyer_authentication/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Integer buyerAuthentication(@PathVariable("prod_idx") Integer prod_idx){ 
			
			LoginMember member = (LoginMember)session.getAttribute("loginId");
			
			//현재 로그인 중인 회원의 상품 구매 리스트 (order by reg_date라서 오래된 구매정보부터 가져옴)
			List<BuyInfo> buyList = etc.buyerAuthentication(member.getMember_id(), prod_idx);
			
			for(BuyInfo bl : buyList) {
				String buy_idx_str = bl.getBuy_idx() + ""; // buylist 테이블의 buy_idx
				Integer buy_idx = Integer.parseInt(buy_idx_str);
				try {					
					
					if(review.getReviewByBuyIdx(buy_idx) == null) {// buy_idx에 해당하는 리뷰가 없다면
						return buy_idx;						
					}
					
				} catch (Exception e) {
					log.info(e.getMessage());
				}
			}
			
			return null; // buy_idx들에 해당하는 리뷰들이 모두 작성 돼 있을 때
		}

	   
	   

	   
	   
	   // 상품 아이디로 상품 상품 리뷰 목록 가져오기 
	   @GetMapping(value="/prod/select_prod_review/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public List<JoinReviewMember> selectReview(@PathVariable("prod_idx") Integer prod_idx) {  
		   return jrm.getReviewMemberListByProdIdx(prod_idx);
	   }

	   //리뷰아디엑스에 해당하는 리뷰픽처 목록 얻기
	   @GetMapping(value="/prod/select_review_picture_list/{review_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public List<ReviewPicture> getReviewPictureByReviewIdx(@PathVariable("review_idx") Integer review_idx) {
		   
		   List<ReviewPicture> reviewPictureList = rp.getReviewPicturebyReviewIdx(review_idx);
		   for(ReviewPicture rp : reviewPictureList) {
			   rp.setReview_picture_url(rp.getReview_picture_url());
		   }
		   return reviewPictureList; 
	   }

	   
	   
	   // member_id로 프로필 이미지 가져오기
	   @GetMapping(value="/prod/get_member_image/{member_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public String getMemberProfile(@PathVariable("member_id") String member_id) {
		   return etc.getMemberImageById(member_id);
	   }

	   
	   
	   
	   // 멤버 아이디로 멤버 닉네임 가져오기(이건 후기 남길 때도 쓰이고, 상품 리스트 띄울 때도 쓰임) 
	   @GetMapping(value = "/prod/get_member_nickname_by_member_id/{member_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public String getMemberNickNameByMemberId(@PathVariable("member_id") String member_id) {   
		   
		   String nickname = member.getMember(member_id).getMember_nickName();		   
		   return nickname;  
	   }

	   
		//리뷰 작성. 이거 호출하는 곳이 팝업창이니까 작업 완료하고 다른 페이지로 넘어갈 필요 없이 그냥 결과 값에 따라 창 닫고 말고 결정하면 되잖음? 그니까 레컨에서 처리하자
		@PostMapping(value="/prod/insert_review", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, String> insert_review(@RequestBody Map<String, Object> param) {

			Map<String, String> resultMap = new HashMap<>();
			 
			String buy_idx_str = (String)param.get("buy_idx");
			Long buy_idx = Long.valueOf(Integer.parseInt(buy_idx_str));
			
			LoginMember member = (LoginMember)session.getAttribute("loginId");
			String member_id = member.getMember_id();
			Integer prod_idx = Integer.parseInt((String)param.get("prod_idx"));
			String file_paths = (String)param.get("file_paths");
			String review_content = (String)param.get("review_content");
			Integer review_starnum = Integer.parseInt((String)param.get("review_starnum"));
			
			
		   Long datetime = System.currentTimeMillis();
	       Timestamp timestamp = new Timestamp(datetime);
		
			try {
				review.insertReviewWithJavaTS(prod_idx, member_id, review_content, timestamp, review_starnum, buy_idx); 
				
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
		
		
		
		//후기 사진 업로드 (multipart/form-data 한글 깨짐 오류 때문에 결국 파일 업로드만 따로 함. 파일명도 깨져서 서버에 업로드할 때 원본 파일명은 유지할 수 없을 듯)
		@PostMapping(value="/prod/upload_review_image", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, List<String>> uploadReviewImage(	HttpServletRequest request,
									@RequestParam("attach_file") List<MultipartFile> multipartFile) throws UnsupportedEncodingException {
			
			Map<String, List<String>> resultMap = new HashMap<>();
	
			List<String> targetFilesNames = new ArrayList<>();   
			
			String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");

			String fileRoot;
			try {
				// 파일이 있을때 탄다.
				if(multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
					
					for(MultipartFile file:multipartFile) {
						fileRoot = contextRoot + "resources/upload/prod_review_img/";
						//System.out.println(fileRoot); //C:\JavaAWS\spring-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\project-farmocean\resources/upload/prod_review_img/
						
						String originalFileName = file.getOriginalFilename();	//오리지날 파일명
						String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
						String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
						
						//String targetFileStr = fileRoot + savedFileName;
						String targetFileStr = "/resources/upload/prod_review_img/" + savedFileName;
						File targetFile = new File(fileRoot + savedFileName);	
						try {
							InputStream fileStream = file.getInputStream();
							FileUtils.copyInputStreamToFile(fileStream, targetFile); //파일 저장
							targetFilesNames.add(targetFileStr);							
						} catch (Exception e) {
							//파일삭제
							FileUtils.deleteQuietly(targetFile);	//저장된 현재 파일 삭제
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

	   
		//리뷰 삭제(리뷰, 리뷰 사진, 사진 파일)
		@DeleteMapping(value="/delete_review/{review_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, Integer> deleteReview(@PathVariable("review_idx") Integer review_idx) {
			
			Map<String, Integer> resultMap = new HashMap<>();
			
						
			//리뷰사진 삭제, 사진 파일 삭제
			try {
				//review_idx에 해당하는 리뷰 사진 url 리스트 가져오기
				// 리뷰 사진 목록 가져오는데 없을 수도 있으니까 이것도 트라이캐치문 안에
				List<ReviewPicture> reviewPictureList = rp.getReviewPicturebyReviewIdx(review_idx);
				
				//리뷰 사진 url 리스트 for문 돌려서 리뷰에 해당하는 사진파일들 서버에서 삭제
				for(ReviewPicture img : reviewPictureList) {
		        	
					String img_url = img.getReview_picture_url(); 
					
					//업로드된 파일은 나중에 삭제해야 됨!(서버 경로 일치 하지 않으면 사진파일 없으니까)
					String fullPath = req.getServletContext().getRealPath("/") + img_url.replace("/", "\\");
					
		        	File file = new File(fullPath);
		        	
		        	if(file.exists()) {    //삭제하고자 하는 파일이 해당 서버에 존재하면 삭제시킨다
			            file.delete();
			        }
		        	
		        	//리뷰에 해당하는 사진 데이터 DB에서 삭제 (fk 먼저 지운 다음에 pk지우는 거)
					rp.deleteReviewPicture(review_idx);
		        }
				
				
				//리뷰 삭제 
				review.deleteReviewByReviewIdx(review_idx);

				
		        
				resultMap.put("result", 1);
				 
			} catch(Exception e) {
				log.info(e.getMessage());
				resultMap.put("result", -1);
				return resultMap;
			}
			
			return resultMap;
		}
		
	   
//___________________________________________________________댓글__________________________________________________________	   
	
	   // prod_idx에 해당하는 댓글 리스트 가져오기. 
	   @GetMapping(value="/prod/select_prod_comment/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public List<ProductComment> selectComment(@PathVariable("prod_idx") Integer prod_idx) {		   		   
		   return getCommentList(prod_idx);
	   }

	   
	   
	   // prod_idx에 해당하는 댓글 리스트 가져오기 (그냥 일반 메서드)
	   public List<ProductComment> getCommentList(Integer prod_idx) {
			  
		   LoginMember member = (LoginMember) session.getAttribute("loginId");
		   String member_id = null;
		   List<ProductComment> commentList = null;
		   
		   String sellerId = prod.getSellerIdByProdIdx(prod_idx);
		   
		   
		   try {
			   member_id = member.getMember_id();
		   } catch(Exception e) {
			   System.out.println(e.getMessage());
		   }
		   
		   if(member_id == null) {
			   comment.updateNonUserCommentAccessible(prod_idx);
		   } else {
			   if(member_id.equals(sellerId)) {
				   comment.updateSellerCommentAccessible(prod_idx);
			   } else {				   
				   comment.updateUserCommentAccessible(prod_idx, member_id);
			   }
		   }
		   
		   try {
			   commentList = comment.getCommentListByProdIdx(prod_idx);
		   } catch(Exception e) {
			   System.out.println(e.getMessage());
		   }

		   return commentList;
	   }

	   
	   // 댓글 등록
	   @PostMapping(value = "/prod/insert_comment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Integer insertReview(@RequestBody ProductComment productComment) {

		   List<ProductComment> commentList = null; 
		   LoginMember member = (LoginMember)session.getAttribute("loginId");
		   if(member== null) {
			   return 2; //현재 로그인 중인 계정 없음
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
	   
	   
	   
	   // 상품 댓글 삭제
	   @GetMapping(value = "/prod/delete_comment/{prod_idx}/{comment_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Integer deleteComment(@PathVariable("prod_idx") Integer prod_idx, @PathVariable("comment_idx") Integer comment_idx) {
//		   System.out.println(comment_idx + "/" + prod_idx);
		   return comment.deleteComment(comment_idx);
		   //return getCommentList(prod_idx);
	   }
	   
	   
	   // 상품 댓글에 답글 등록
	   @PostMapping(value = "/prod/update_comment_reply", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Integer updateCommentReply(@RequestBody ProductComment productComment) {	   
		   
		   Integer commentIdx = productComment.getComment_idx();
		   String commentReply = productComment.getComment_reply();
		   System.out.println(commentIdx + "/" + commentReply);
		   
		   return comment.updateCommentReply(commentIdx, commentReply);
	   }
	
	   
//______________________________________________________ETC______________________________________________________________

	   
	   @Autowired
	   FollowService followService;
	   
	   @PostMapping(value="/follow", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Map<String, Integer> follow(@RequestBody Follow follow) {
		   Map<String, Integer> map = new HashMap<>();
		   
		   LoginMember member = (LoginMember) session.getAttribute("loginId");
		   if(member == null) {
			   map.put("result", 0); //로그인 중인 계정 없음
		   } else {
			   //중복 확인 (etc매퍼)
			   Boolean duplicate = etc.getFollow(member.getMember_id(), follow.getFollowee_id()).size() > 0 ? true : false;
			   
			   if(duplicate) {
				   map.put("result", 2); //이미 팔로우하고 있음
			   } else {
				   follow.setFollower_id(member.getMember_id());
				   followService.insert(follow);
				   map.put("result", 1);
			   }			   
		   }
		   
		   return map;
	   }
	   
	   
	   @DeleteMapping(value="/unfollow", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Map<String, Integer> unfollow(@RequestBody Follow follow) {
		   Map<String, Integer> map = new HashMap<>();
		   
		   LoginMember member = (LoginMember) session.getAttribute("loginId");
		   if(member == null) {
			   map.put("result", 0); //로그인 중인 계정 없음 
		   } else {
			   Boolean following = etc.getFollow(member.getMember_id(), follow.getFollowee_id()).size() > 0 ? true : false;
			   
			   if(following) {
				   follow.setFollower_id(member.getMember_id());
				   followService.delete(follow);
				   map.put("result", 1); //팔로우 취소됨
			   } else {
				   map.put("result", 2); //이미 언팔중			   
			   }		   			   
		   }
		   
		   return map;
	   }
   


// ETC_____________________________________________________________________________________________________

	   
	   @Autowired
	   FollowServiceImpl follow;
	   
	   @Autowired
	   JsonProdServiceImpl jsonProdService;
	   
	   // 팔로우 중인지 체크
	   @GetMapping(value="/is_following/{followee_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Integer getFollowerList(@PathVariable("followee_id") String followee_id) {		   		   
		   
		   LoginMember member = (LoginMember) session.getAttribute("loginId");
		   if(member == null) {
			   return 0; //로그인 중이 아님
		   }
		   String loginId = member.getMember_id();
		   
		   
		   List<Follow> followList = follow.getFollowerList(followee_id);
		   for(Follow follow : followList) {
			   if(follow.getFollower_id().equals(loginId)) {
				   return 1; // 팔로우 중이면 1
			   }
		   }
		   return -1; // 팔로우중이 아니면 0
	   }

	   // 찜한 상품인지 체크
	   @GetMapping(value="/is_heart_prod/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Integer isHeartProd(@PathVariable("prod_idx") Integer prod_idx) {		   		   
		   
		   LoginMember member = (LoginMember) session.getAttribute("loginId");
		   if(member == null) {
			   return 0; //로그인 중이 아님
		   }
		   String loginId = member.getMember_id();
		   
		   //찜체크
		   if(jsonProdService.getProdBidsChk(prod_idx, loginId) > 0) {
			   return 1; //찜 된 상품
		   } else {
			   return -1; //찜 안 된 상품
		   }
	   }
	   




}
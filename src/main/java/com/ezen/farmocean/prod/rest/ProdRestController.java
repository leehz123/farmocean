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
	
	
   @Autowired
   ProdImgServiceImpl prodImgService;

	   
	   
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

		
	   
		//��ǰ ���� ���ε� (multipart/form-data �ѱ� ���� ���� ������ �ᱹ ���� ���ε常 ���� ��. ���ϸ� ������ ������ ���ε��� �� ���� ���ϸ��� ������ �� ���� ��)
		@PostMapping(value="/prod/upload_prod_image", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, List<String>> uploadProdImage(HttpServletRequest request,
									@RequestParam("attach_file") List<MultipartFile> multipartFile) throws UnsupportedEncodingException {
			
			Map<String, List<String>> resultMap = new HashMap<>();
			
			List<String> targetFilesNames = new ArrayList<>();   
			
			String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
			String fileRoot;
			try {
				// ������ ������ ź��.
				if(multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
					
					for(MultipartFile file:multipartFile) {
						fileRoot = contextRoot + "resources/upload/prod_detail_img/"; //C:\JavaAWS\spring-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\project-farmocean\resources/upload/prod_review_img/
						
						String originalFileName = file.getOriginalFilename();	//�������� ���ϸ�
						String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//���� Ȯ����
						String savedFileName = UUID.randomUUID() + extension;	//����� ���� ��
						
						
						File targetFile = new File(fileRoot + savedFileName);	
						try {
							InputStream fileStream = file.getInputStream();
							FileUtils.copyInputStreamToFile(fileStream, targetFile); //���� ����
							targetFilesNames.add("/resources/upload/prod_detail_img/" + targetFile.getName());	
							
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

	   
	   
	   
	   	//http://localhost:8888/farmocean/product/insert_prod
		@RequestMapping(value = "/insert_prod", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, Integer> insert_prod (@RequestBody Map<String, Object> param) throws ParseException {

			Map<String, Integer> map = new HashMap<>(); 
			System.out.println("�����н��� : " + req.getParameter("file-paths"));			
			
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
	        	prod_info = "<p>��ǰ �󼼳��� �غ� ���Դϴ�.</p>";
	        } else {
	        	prod_info = (String)param.get("prod_info");
	        }
	        
	        Integer prod_price = Integer.parseInt((String)param.get("prod_price"));
	        Integer prod_stock = Integer.parseInt((String)param.get("prod_stock"));
	        Integer cate_idx = Integer.parseInt((String)param.get("cate_idx"));
	        
	        //�ۼ��� Ÿ�ӽ����� ���ϱ�
	        Date today = new Date();
	        long todayTime = today.getTime();
	        Timestamp prod_written_date = new Timestamp(todayTime);
	        
	 

	        try {
	        	prod.insertProduct(member_id, prod_name, prod_info, cate_idx, "�Ǹ���", prod_price, prod_sell_deadline, prod_stock, 0, 0, prod_written_date);
	        	//pService.addProduct(member_id, prod_name, prod_info, cate_idx, prod_price, prod_sell_deadline, prod_stock, 0, 0, prod_written_date);
	        	
	        	Integer prod_idx = prod.getProdIdxByIdAndDate(member_id, prod_written_date); 
	            map.put("prod_idx", prod_idx);
	            
				String[] filePathsArr = file_paths.split("#");
				for(int i = 0; i < filePathsArr.length; ++i) {
					if(i == 0) {
						prodImgService.insertProdImg(prod_idx, filePathsArr[i], 1); //�����н� ����� �� �� �� ó�� �̹����н��� ��ǥ�̹����� �� ��
					} else {
						prodImgService.insertProdImg(prod_idx, filePathsArr[i], 0);
					}
				}
				map.put("result", 1); //��ǰ �߰� ����
	        } catch (Exception e) {
	        	log.info(e.getMessage());
	        	map.put("result", -1); //��ǰ �߰� ����
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
			
			//���̺� �ִ� url �� ���� 
			prodImgService.deleteProdImgByProd_idx(prod_idx);
			
			//�̹��� ÷�� �ٲ�� ������
			if(!filePathsStr.equals("noImgChange")) {
				//�̸����⿡�� ���� �� img_url ���ϵ� ����
				try {					
					String[] deletedOldImgsArr = deletedOldImgStr.split("#");
					for(String doi : deletedOldImgsArr) {
						//������ �̹��� url DB���� �����
						prodImgService.deleteImgByImgURL(doi);
						
						//�̹��� ���� ���� 
						String fullPath = req.getServletContext().getRealPath("/") + doi.replace("/", "\\");
			        	File file = new File(fullPath);
			        	
			        	if(file.exists()) {    //�����ϰ��� �ϴ� ������ �ش� ������ �����ϸ� ������Ų��
				            file.delete();
				        }
					}
				} catch(Exception e) {
					log.info(e.getMessage());
				}
				
				
				if(filePathsStr.length() != 0) {
					String[] filePathsArr = filePathsStr.split("#");
					
					//filePaths �迭 for �� ������ prod_img���̺� ������ ����
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

	        //�ۼ��� Ÿ�ӽ����� ���ϱ�
	        Date today = new Date();
	        long todayTime = today.getTime();
	        Timestamp prod_written_date = new Timestamp(todayTime);	        
	        
	        try {
	        	prod.updateProduct(prod_idx, prod_name, prod_info, cate_idx, "�Ǹ���", prod_price, prod_sell_deadline, prod_stock, 0, prod_written_date);
	        	//pService.editProduct(prod_idx, prod_name, prod_info, cate_idx, prod_price, prod_sell_deadline, prod_stock, 0, prod_written_date);
	        	
	        	map.put("result", 1); //��ǰ �߰� ����
	        	map.put("prod_idx", prod_idx);
	        } catch (Exception e) {
	        	log.info(e.getMessage());
	        	map.put("result", -1); //��ǰ �߰� ����
	        	return map;        	
	        }
	        
	        return map; 
		}

		// ��ǰ ����, ��� ����(���ϰ��, prod_idx��)
		@RequestMapping(value = "/delete_prod_img_1/{prod_idx}/{file_path}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, Integer> delete_prod(Model model, HttpServletRequest req, @PathVariable("prod_idx") Integer prod_idx, @PathVariable("file_path") String file_path) throws ParseException {
			
			Map<String, Integer> map = new HashMap<>();
			
			try {

				// �̹��� ���� ����
				List<ProdImg> prodImgList =  prodImgService.getImgsByProdIdx(prod_idx);
		        for(ProdImg img : prodImgList) {
		        	
		        	// ProdImg ���̺� �� ����
					prodImgService.deleteProdImgByProd_idx(prod_idx);
		        	
		        	
		        	String fullPath = req.getServletContext().getRealPath("/") + img.getImg_url().replace("/", "\\");
		        	System.out.println(fullPath);
		        	File file = new File(fullPath);
		        	
		        	if(file.exists()) {    //�����ϰ��� �ϴ� ������ �ش� ������ �����ϸ� ������Ų��
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

		
		
		//��ǰ ���� + ��ǰ �̹��� ��� ���� + ������ ���ε� �� �̹��� ����
		@RequestMapping(value = "/delete_prod/{prod_idx}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, Integer> delete_prod(Model model, HttpServletRequest req, @PathVariable("prod_idx") Integer prod_idx) throws ParseException {
			
			Map<String, Integer> map = new HashMap<>();
			
			try {
				
				//��ǰ ���� ���� 2(������)�� ����
				prod.updateProductStatusDelete(prod_idx);
				
				
				// ���⼭���ʹ� ��ǥ �������� Ǯ�� ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				
				/*

				// �̹��� ���� ����
				List<ProdImg> prodImgList =  prodImgService.getImgsByProdIdx(prod_idx);
		        for(ProdImg img : prodImgList) {
		        	
		        	//ProdImg ���̺� �� ����
					prodImgService.deleteProdImgByProd_idx(prod_idx);
		        	
		        	String fullPath = req.getServletContext().getRealPath("/") + img.getImg_url().replace("/", "\\");
		        	System.out.println(fullPath);
		        	File file = new File(fullPath);
		        	
		        	if(file.exists()) {    //�����ϰ��� �ϴ� ������ �ش� ������ �����ϸ� ������Ų��
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
	   
	   
	   // ��ǰ ���̵�� ��ǰ ���� ��� ��� ��������
	   @GetMapping(value="/prod/get_product_img/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public List<ProdImg> getproductImgByprodIdx(@PathVariable Integer prod_idx) {
		   List<ProdImg> imgList = prodImgService.getImgsByProdIdx(prod_idx);
		   return imgList;
	   }
	   
	   
	   //�Ǹ������ ��ǰ prod_sell '�Ǹ�����'�� ����
//	   @GetMapping(value="/product/expire_deadline/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	   public Integer expireDeadline(@PathVariable("prod_idx") Integer prod_idx) { 
//		   return prod.expireDeadline(prod_idx);
//	   }
	   
	   
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
	            
	            //map�� �������ϸ�(Ȯ����x), ���ε� �� 1, ���� URL ���� 
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
	   

	   
	   

		//���� ����� ���� ���� ����   
		@GetMapping(value="/buyer_authentication/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Integer buyerAuthentication(@PathVariable("prod_idx") Integer prod_idx){ 
			
			LoginMember member = (LoginMember)session.getAttribute("loginId");
			
			//���� �α��� ���� ȸ���� ��ǰ ���� ����Ʈ (order by reg_date�� ������ ������������ ������)
			List<BuyInfo> buyList = etc.buyerAuthentication(member.getMember_id(), prod_idx);
			
			for(BuyInfo bl : buyList) {
				String buy_idx_str = bl.getBuy_idx() + ""; // buylist ���̺��� buy_idx
				Integer buy_idx = Integer.parseInt(buy_idx_str);
				try {					
					
					if(review.getReviewByBuyIdx(buy_idx) == null) {// buy_idx�� �ش��ϴ� ���䰡 ���ٸ�
						return buy_idx;						
					}
					
				} catch (Exception e) {
					log.info(e.getMessage());
				}
			}
			
			return null; // buy_idx�鿡 �ش��ϴ� ������� ��� �ۼ� �� ���� ��
		}

	   
	   

	   
	   
	   // ��ǰ ���̵�� ��ǰ ��ǰ ���� ��� �������� 
	   @GetMapping(value="/prod/select_prod_review/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public List<JoinReviewMember> selectReview(@PathVariable("prod_idx") Integer prod_idx) {  
		   return jrm.getReviewMemberListByProdIdx(prod_idx);
	   }

	   //����Ƶ𿢽��� �ش��ϴ� ������ó ��� ���
	   @GetMapping(value="/prod/select_review_picture_list/{review_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public List<ReviewPicture> getReviewPictureByReviewIdx(@PathVariable("review_idx") Integer review_idx) {
		   
		   List<ReviewPicture> reviewPictureList = rp.getReviewPicturebyReviewIdx(review_idx);
		   for(ReviewPicture rp : reviewPictureList) {
			   rp.setReview_picture_url(rp.getReview_picture_url());
		   }
		   return reviewPictureList; 
	   }

	   
	   
	   // member_id�� ������ �̹��� ��������
	   @GetMapping(value="/prod/get_member_image/{member_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public String getMemberProfile(@PathVariable("member_id") String member_id) {
		   return etc.getMemberImageById(member_id);
	   }

	   
	   
	   
	   // ��� ���̵�� ��� �г��� ��������(�̰� �ı� ���� ���� ���̰�, ��ǰ ����Ʈ ��� ���� ����) 
	   @GetMapping(value = "/prod/get_member_nickname_by_member_id/{member_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public String getMemberNickNameByMemberId(@PathVariable("member_id") String member_id) {   
		   
		   String nickname = member.getMember(member_id).getMember_nickName();		   
		   return nickname;  
	   }

	   
		//���� �ۼ�. �̰� ȣ���ϴ� ���� �˾�â�̴ϱ� �۾� �Ϸ��ϰ� �ٸ� �������� �Ѿ �ʿ� ���� �׳� ��� ���� ���� â �ݰ� ���� �����ϸ� ������? �״ϱ� �������� ó������
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
		
		
		
		//�ı� ���� ���ε� (multipart/form-data �ѱ� ���� ���� ������ �ᱹ ���� ���ε常 ���� ��. ���ϸ� ������ ������ ���ε��� �� ���� ���ϸ��� ������ �� ���� ��)
		@PostMapping(value="/prod/upload_review_image", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, List<String>> uploadReviewImage(	HttpServletRequest request,
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
						
						//String targetFileStr = fileRoot + savedFileName;
						String targetFileStr = "/resources/upload/prod_review_img/" + savedFileName;
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

	   
		//���� ����(����, ���� ����, ���� ����)
		@DeleteMapping(value="/delete_review/{review_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<String, Integer> deleteReview(@PathVariable("review_idx") Integer review_idx) {
			
			Map<String, Integer> resultMap = new HashMap<>();
			
						
			//������� ����, ���� ���� ����
			try {
				//review_idx�� �ش��ϴ� ���� ���� url ����Ʈ ��������
				// ���� ���� ��� �������µ� ���� ���� �����ϱ� �̰͵� Ʈ����ĳġ�� �ȿ�
				List<ReviewPicture> reviewPictureList = rp.getReviewPicturebyReviewIdx(review_idx);
				
				//���� ���� url ����Ʈ for�� ������ ���信 �ش��ϴ� �������ϵ� �������� ����
				for(ReviewPicture img : reviewPictureList) {
		        	
					String img_url = img.getReview_picture_url(); 
					
					//���ε�� ������ ���߿� �����ؾ� ��!(���� ��� ��ġ ���� ������ �������� �����ϱ�)
					String fullPath = req.getServletContext().getRealPath("/") + img_url.replace("/", "\\");
					
		        	File file = new File(fullPath);
		        	
		        	if(file.exists()) {    //�����ϰ��� �ϴ� ������ �ش� ������ �����ϸ� ������Ų��
			            file.delete();
			        }
		        	
		        	//���信 �ش��ϴ� ���� ������ DB���� ���� (fk ���� ���� ������ pk����� ��)
					rp.deleteReviewPicture(review_idx);
		        }
				
				
				//���� ���� 
				review.deleteReviewByReviewIdx(review_idx);

				
		        
				resultMap.put("result", 1);
				 
			} catch(Exception e) {
				log.info(e.getMessage());
				resultMap.put("result", -1);
				return resultMap;
			}
			
			return resultMap;
		}
		
	   
//___________________________________________________________���__________________________________________________________	   
	
	   // prod_idx�� �ش��ϴ� ��� ����Ʈ ��������. 
	   @GetMapping(value="/prod/select_prod_comment/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public List<ProductComment> selectComment(@PathVariable("prod_idx") Integer prod_idx) {		   		   
		   return getCommentList(prod_idx);
	   }

	   
	   
	   // prod_idx�� �ش��ϴ� ��� ����Ʈ �������� (�׳� �Ϲ� �޼���)
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
	
	   
//______________________________________________________ETC______________________________________________________________

	   
	   @Autowired
	   FollowService followService;
	   
	   @PostMapping(value="/follow", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Map<String, Integer> follow(@RequestBody Follow follow) {
		   Map<String, Integer> map = new HashMap<>();
		   
		   LoginMember member = (LoginMember) session.getAttribute("loginId");
		   if(member == null) {
			   map.put("result", 0); //�α��� ���� ���� ����
		   } else {
			   //�ߺ� Ȯ�� (etc����)
			   Boolean duplicate = etc.getFollow(member.getMember_id(), follow.getFollowee_id()).size() > 0 ? true : false;
			   
			   if(duplicate) {
				   map.put("result", 2); //�̹� �ȷο��ϰ� ����
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
			   map.put("result", 0); //�α��� ���� ���� ���� 
		   } else {
			   Boolean following = etc.getFollow(member.getMember_id(), follow.getFollowee_id()).size() > 0 ? true : false;
			   
			   if(following) {
				   follow.setFollower_id(member.getMember_id());
				   followService.delete(follow);
				   map.put("result", 1); //�ȷο� ��ҵ�
			   } else {
				   map.put("result", 2); //�̹� ������			   
			   }		   			   
		   }
		   
		   return map;
	   }
   


// ETC_____________________________________________________________________________________________________

	   
	   @Autowired
	   FollowServiceImpl follow;
	   
	   @Autowired
	   JsonProdServiceImpl jsonProdService;
	   
	   // �ȷο� ������ üũ
	   @GetMapping(value="/is_following/{followee_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Integer getFollowerList(@PathVariable("followee_id") String followee_id) {		   		   
		   
		   LoginMember member = (LoginMember) session.getAttribute("loginId");
		   if(member == null) {
			   return 0; //�α��� ���� �ƴ�
		   }
		   String loginId = member.getMember_id();
		   
		   
		   List<Follow> followList = follow.getFollowerList(followee_id);
		   for(Follow follow : followList) {
			   if(follow.getFollower_id().equals(loginId)) {
				   return 1; // �ȷο� ���̸� 1
			   }
		   }
		   return -1; // �ȷο����� �ƴϸ� 0
	   }

	   // ���� ��ǰ���� üũ
	   @GetMapping(value="/is_heart_prod/{prod_idx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	   public Integer isHeartProd(@PathVariable("prod_idx") Integer prod_idx) {		   		   
		   
		   LoginMember member = (LoginMember) session.getAttribute("loginId");
		   if(member == null) {
			   return 0; //�α��� ���� �ƴ�
		   }
		   String loginId = member.getMember_id();
		   
		   //��üũ
		   if(jsonProdService.getProdBidsChk(prod_idx, loginId) > 0) {
			   return 1; //�� �� ��ǰ
		   } else {
			   return -1; //�� �� �� ��ǰ
		   }
	   }
	   




}
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

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class BoardRestController {
	
	@Autowired
	BoardService board;
	
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
        // 랜덤 문자 생성
        UUID uid = UUID.randomUUID();
        
        OutputStream out = null;
        
        //인코딩
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        Map<String, String> result = new HashMap<>();
        
        try{
            
            //파일 이름 가져오기
            //String fileName = upload.getOriginalFilename();
            String fileName = upload.getOriginalFilename();
            String fileExt = fileName.substring(fileName.lastIndexOf('.') + 1);
            byte[] bytes = upload.getBytes();
            
            //이미지 경로 생성
            String uploadPath = "resources\\upload\\" + uploadFolder + "\\";
            String path = request.getServletContext().getRealPath("/") + uploadPath;// fileDir는 전역 변수라 그냥 이미지 경로 설정해주면 된다.
            String fileFullName = uid + "." + fileExt;
            String ckUploadPath = path + fileFullName;
            File folder = new File(path);
            
            log.info(request.getServletContext().getRealPath(ckUploadPath));
            log.info(path);
                        
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

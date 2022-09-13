package com.ezen.farmocean.admin.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezen.farmocean.admin.dto.Banner;
import com.ezen.farmocean.admin.service.JsonProdService;
import com.ezen.farmocean.cs.service.CommonFunction;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class AdminController {
	
	@Autowired
	CommonFunction cf;
	
	@Autowired
	JsonProdService service;
	
	@PostMapping("/admin/setMainTopBanner")
	public void setMainTopBanner(MultipartHttpServletRequest multiReq) throws Exception {
		
		Map<String , MultipartFile> arrReq = multiReq.getFileMap();
		
		Iterator<Entry<String , MultipartFile>> itr = arrReq.entrySet().iterator();
		
		List<Banner> bannerList = new ArrayList<>();
		
		String bannerCate = "maintop";
	
		for(int i = 0, maxCnt = multiReq.getParameterValues("mainTopName").length; i < maxCnt; i++) {
			
			Integer bannerIdx = cf.chkNull(multiReq.getParameterValues("mainTopIdx")[i]) ? 0 : Integer.parseInt(multiReq.getParameterValues("mainTopIdx")[i]); 
			
			bannerList.add(new Banner(bannerIdx, bannerCate, 0, multiReq.getParameterValues("mainTopName")[i], multiReq.getParameterValues("mainTopLink")[i], multiReq.getParameterValues("mainTopFileName")[i]));
		}
		
		log.info(bannerList);
		
		int chkNum = 0;
		
		String savePath = "\\JavaAWS\\project-farmocean\\src\\main\\webapp\\resources\\image\\mainbanner";
		String saveFileName = "";
		
		while(itr.hasNext()) {
			
			Entry<String , MultipartFile> entry = itr.next();
			
			MultipartFile mFile = entry.getValue();			
			
			if(!cf.chkNull(mFile.getOriginalFilename())) {
				
				UUID uid = UUID.randomUUID();
				
				log.info("File : " + mFile.getOriginalFilename());
				String fileName = mFile.getOriginalFilename();
				String fileCutName = fileName.substring(0, fileName.lastIndexOf('.'));
				String fileExt = fileName.substring(fileName.lastIndexOf('.') + 1);
				saveFileName = uid + "_" + fileName;
				
				String saveFullPath = savePath + File.separator + saveFileName;
				
				File fileFolder = new File(savePath);
				
				if(!fileFolder.exists()) {
					if(fileFolder.mkdir()){
						log.info("폴더 생성 성공 : " +  savePath);
					}else {
						log.info("폴더 생성 실패 : " +  savePath);
					}
				}
				
				File saveFile = new File(saveFullPath);
				if(saveFile.isFile()) {
					boolean _exits = true;
					
					int index = 0;
					
					while(_exits) {
						
						index++;
						
						saveFileName = uid + "_" + fileCutName + "(" + index++ +")." + fileExt;
						
						String dictFile = savePath + File.separator + saveFileName;
						
						_exits = new File(dictFile).isFile();
						
						if(!_exits) {
							saveFullPath = dictFile;
						}						
					}
					
					mFile.transferTo(new File(saveFullPath));
					
				}else {
					
					mFile.transferTo(saveFile);
				}
				
				bannerList.get(chkNum).setFilename("/resources/image/mainbanner/" + saveFileName);
				
				log.info("파일 경로 : " + saveFullPath);
				
			}else {				
				
				log.info("File Null : " + chkNum);
			}
			
			chkNum++;
		}
		
		for(Banner b : bannerList) {
			if(b.getIdx() == 0) {
				log.info("추가 : " + service.setMainTopBanner(b));
			}else {
				log.info("수정 : ");
			}
		}
		
		log.info(bannerList);
		
	}

}

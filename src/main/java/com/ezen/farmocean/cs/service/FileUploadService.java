package com.ezen.farmocean.cs.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	
	public String fileUpload(MultipartFile mFile, String savePath) throws Exception;

}

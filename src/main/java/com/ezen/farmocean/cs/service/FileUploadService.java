package com.ezen.farmocean.cs.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	
	/**
	 * 파일 업로드
	 * @param mFile 파일
	 * @param savePath 저장 경로
	 * @return
	 * @throws Exception
	 */
	public String fileUpload(MultipartFile mFile, String savePath) throws Exception;
	/**
	 * 파일 삭제
	 * @param saveRoot 저장 드라이브명
	 * @param savePath 저장 경로
	 * @param fileName 파일 이름
	 * @return
	 */
	public boolean fileDelete(String saveRoot, String savePath, String fileName);
	
	/**
	 * 현재 사용중인 드라이브명 가져오기
	 * @return
	 */
	public String getDriver();

}

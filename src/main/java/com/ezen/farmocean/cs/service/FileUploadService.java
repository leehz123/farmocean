package com.ezen.farmocean.cs.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	
	/**
	 * ���� ���ε�
	 * @param mFile ����
	 * @param savePath ���� ���
	 * @return
	 * @throws Exception
	 */
	public String fileUpload(MultipartFile mFile, String savePath) throws Exception;
	/**
	 * ���� ����
	 * @param saveRoot ���� ����̺��
	 * @param savePath ���� ���
	 * @param fileName ���� �̸�
	 * @return
	 */
	public boolean fileDelete(String saveRoot, String savePath, String fileName);
	
	/**
	 * ���� ������� ����̺�� ��������
	 * @return
	 */
	public String getDriver();

}

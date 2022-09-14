package com.ezen.farmocean.cs.service;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Override
	public String fileUpload(MultipartFile mFile, String savePath) throws Exception {
				
		UUID uid = UUID.randomUUID();

		String fileName = mFile.getOriginalFilename();
		String fileCutName = fileName.substring(0, fileName.lastIndexOf('.'));
		String fileExt = fileName.substring(fileName.lastIndexOf('.') + 1);
		String saveFileName = uid + "_" + fileName;
		
		String saveFullPath = savePath + File.separator + saveFileName;
		
		File fileFolder = new File(savePath);
		
		if(!fileFolder.exists()) {
			fileFolder.mkdir();
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
		
		return saveFileName;
	}
	

}

package org.fourstack.fileupload.service;

import org.fourstack.fileupload.entity.Document;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileApiService {
	
	public String uploadFileToDatabase(MultipartFile file);
	
	public Document downloadFileFromDatabase(String fileName);
	
	public String uploadFileToLocalFileSystem(MultipartFile file);
	
	public Resource downloadFileFromLocalSystem(String fileName);
}

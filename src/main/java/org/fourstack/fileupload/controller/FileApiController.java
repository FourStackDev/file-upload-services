package org.fourstack.fileupload.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.fourstack.fileupload.config.FileUploadBasePath;
import org.fourstack.fileupload.entity.Document;
import org.fourstack.fileupload.payload.UploadFileResponse;
import org.fourstack.fileupload.service.FileApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@FileUploadBasePath
public class FileApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileApiController.class);
	
	@Autowired
	private FileApiService fileApiService;
	
	/*
	 * *****************************************************************************
	 * ******** Start of File Uploading and Downloading - Database System **********
	 * *****************************************************************************
	 */
	@PostMapping("/uploadFiles/db")
	public ResponseEntity<UploadFileResponse> uploadFileToDB(@RequestParam("file") MultipartFile file) {
		String fileName = fileApiService.uploadFileToDatabase(file);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/file-uploads/api/files/downloadFiles/db/")
				.path(fileName).toUriString();
		
		return new ResponseEntity<UploadFileResponse>(
				new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()),
				HttpStatus.CREATED);
	}
	
	@GetMapping("/downloadFiles/db/{fileName:.+}")
	public ResponseEntity<byte[]> downloadFileFromDB(@PathVariable String fileName) {
		Document document = fileApiService.downloadFileFromDatabase(fileName);
		String contentType = "application/octet-stream";
		
		String headerValues = "attachment; filename=\""+fileName+"\"";
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, headerValues)
				.body(document.getFileContent());
	}
	
	/*
	 * *****************************************************************************
	 * ******** End of File Uploading and Downloading - Database System ************
	 * *****************************************************************************
	 */
	
	
	
	/*
	 * *****************************************************************************
	 * ******** Start of File Uploading and Downloading - Local File System ********
	 * *****************************************************************************
	 */
	/**
	 * Method to upload file to the local file System
	 * 
	 * @param file File that needs to be uploaded for local file system
	 * @return
	 */
	@PostMapping("/uploadFiles/local")
	public ResponseEntity<UploadFileResponse> uploadFileToLocalSystem(@RequestParam("file") MultipartFile file) {
		String fileName = fileApiService.uploadFileToLocalFileSystem(file);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/file-uploads/api/files/downloadFiles/local/")
				.path(fileName)
				.toUriString();
		
		return new ResponseEntity<UploadFileResponse>(
				new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()),
				HttpStatus.CREATED);
	}
	
	
	@GetMapping("/downloadFiles/local/{fileName:.+}")
	public ResponseEntity<Resource> downloadFileFromLocalSystem(@PathVariable String fileName, HttpServletRequest request) {
		Resource resource = fileApiService.downloadFileFromLocalSystem(fileName);
		
		String contentType = null;
		String headerValues = "attachment; filename=\"" + resource.getFilename() + "\"";
		
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			logger.error("IOException occurred while fetching the contentType");
		}
		
		if (contentType == null)
			contentType = "application/octet-stream";
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, headerValues)
				.body(resource);
	}
	
	/*
	 * *****************************************************************************
	 * ********* End of File Uploading and Downloading - Local File System *********
	 * *****************************************************************************
	 */
}

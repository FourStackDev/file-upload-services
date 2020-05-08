package org.fourstack.fileupload.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.fourstack.fileupload.annotations.FileUploadBasePath;
import org.fourstack.fileupload.entity.Document;
import org.fourstack.fileupload.exceptionhandling.HttpResponseStatusException;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Class <b><i>FileApiController</i></b> is a Spring Boot REST Controller
 * annotation implemented class, which acts as API end point for the
 * File-Upload-Services Application.<br/>
 * 
 * @author Manjunath_HM
 *
 */
/*
 * @Api, @ApiResponses, @ApiOperation annotations are from swagger and are used
 * to decorate code to provide API information for the swagger
 */
@RestController
@FileUploadBasePath
@Api(tags = "File Upload Services Controller")
public class FileApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileApiController.class);
	
	@Autowired
	private FileApiService fileApiService;
	
	/*
	 * *****************************************************************************
	 * ******** Start of File Uploading and Downloading - Database System **********
	 * *****************************************************************************
	 */
	
	@ApiOperation(value = "Save the file to the database", consumes = "multipart/form-data", produces = "application/json",
			response = ResponseEntity.class, httpMethod = "POST",
			notes = "End point to save the file to database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully saved the file into database"),
			@ApiResponse(code = 201, message ="Successfully saved the file to database"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Forbidden Access to insert the resource"),
			@ApiResponse(code = 409, message = "Conflict occurred"),
			@ApiResponse(code = 500, message = "Internal Server Error. Not able to process the request")
			
	})
	@PostMapping("/uploadFiles/db")
	public ResponseEntity<UploadFileResponse> uploadFileToDB(
			@RequestParam(value = "file", required = false) MultipartFile file) {
		/*
		 * NOTE : if required value is not provided then its default value is true. At
		 * that time if end user try to hit the endpoint without the file, then he will
		 * get BAD_REQUEST, but actual reason will not be identified. 
		 * Hence the below code is written to return BAD_REQUEST with proper reason
		 */
		if (file == null) {
			throw new HttpResponseStatusException("Missing Request Body: @RequestParam is missing.");
		}

		String fileName = fileApiService.uploadFileToDatabase(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/file-uploads/api/files/downloadFiles/db/").path(fileName).toUriString();

		return new ResponseEntity<UploadFileResponse>(
				new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()),
				HttpStatus.CREATED);
	}
	
	/*  ##################################################################################################  */
	
	@ApiOperation(value = "Save the list of files to the database", consumes = "multipart/form-data", produces = "application/json",
			response = ResponseEntity.class, httpMethod = "POST",
			notes = "End point to save the file to database",
			hidden = true)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully saved the files into database"),
			@ApiResponse(code = 201, message ="Successfully saved the files to database"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Forbidden Access to insert the resources"),
			@ApiResponse(code = 409, message = "Conflict occurred"),
			@ApiResponse(code = 500, message = "Internal Server Error. Not able to process the request")
			
	})
	@PostMapping("/uploadMultipleFiles/db")
	public List<ResponseEntity<UploadFileResponse>> uploadMultipleFilesToDB(@RequestParam(value = "files") MultipartFile[] files) {
		return Arrays.asList(files)
				.stream()
				.map(file -> uploadFileToDB(file))
				.collect(Collectors.toList());
	}
	
	/*  ##################################################################################################  */
	
	@ApiOperation(value = "Download the requested file from database", produces = "application/octet-stream",
			response = ResponseEntity.class, httpMethod = "GET",
			notes = "End point to fetch the file from database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the file from database"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Forbidden Access to insert the resources"),
			@ApiResponse(code = 404, message = "Requested resource not found"),
			@ApiResponse(code = 500, message = "Internal Server Error. Not able to process the request")
			
	})
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
	@ApiOperation(value = "Save the file to the Local file System", consumes = "multipart/form-data", produces = "application/json",
			response = ResponseEntity.class, httpMethod = "POST",
			notes = "End point to save the file to Local file System")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully saved the file into Local file System"),
			@ApiResponse(code = 201, message ="Successfully saved the file to Local file System"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Forbidden Access to insert the resource"),
			@ApiResponse(code = 500, message = "Internal Server Error. Not able to process the request")
			
	})
	@PostMapping("/uploadFiles/local")
	public ResponseEntity<UploadFileResponse> uploadFileToLocalSystem(
			@RequestParam(value = "file", required = false) MultipartFile file) {
		/*
		 * NOTE : if required value is not provided then its default value is true. At
		 * that time if end user try to hit the endpoint without the file, then he will
		 * get BAD_REQUEST, but actual reason will not be identified. 
		 * Hence the below code is written to return BAD_REQUEST with proper reason
		 */
		if (file == null) {
			throw new HttpResponseStatusException("Missing Request Body: @RequestParam is missing.");
		}
		
		String fileName = fileApiService.uploadFileToLocalFileSystem(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/file-uploads/api/files/downloadFiles/local/").path(fileName).toUriString();

		return new ResponseEntity<UploadFileResponse>(
				new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()),
				HttpStatus.CREATED);
	}
	
	/*  ##################################################################################################  */
	
	@ApiOperation(value = "Save the list of files to the Local File System", consumes = "multipart/form-data", produces = "application/json",
			response = ResponseEntity.class, httpMethod = "POST",
			notes = "End point to save the file to Local File System",
			hidden = true)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully saved the files into Local File System"),
			@ApiResponse(code = 201, message ="Successfully saved the files to Local File System"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Forbidden Access to insert the resources"),
			@ApiResponse(code = 500, message = "Internal Server Error. Not able to process the request")
			
	})
	@PostMapping("/uploadMultipleFiles/local")
	public List<ResponseEntity<UploadFileResponse>> uploadMultipleFilesToLocal(@RequestParam(value = "files") MultipartFile[] files) {
		return Arrays.asList(files)
				.stream()
				.map(file -> uploadFileToLocalSystem(file))
				.collect(Collectors.toList());
	}
	
	/*  ##################################################################################################  */
	
	@ApiOperation(value = "Download the requested file from Local File System", produces = "application/octet-stream",
			response = ResponseEntity.class, httpMethod = "GET",
			notes = "End point to fetch the file from Local File System")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the file from Local File System"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Forbidden Access to insert the resources"),
			@ApiResponse(code = 404, message = "Requested resource not found"),
			@ApiResponse(code = 500, message = "Internal Server Error. Not able to process the request")
			
	})
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

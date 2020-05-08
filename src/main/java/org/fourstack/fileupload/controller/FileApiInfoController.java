package org.fourstack.fileupload.controller;

import org.fourstack.fileupload.annotations.FileUploadBasePath;
import org.fourstack.fileupload.entity.AppInfo;
import org.fourstack.fileupload.service.FileApiInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/*
 * @Api, @ApiResponses, @ApiOperation annotations are from swagger and are used
 * to decorate code to provide API information for the swagger
 */

@RestController
@FileUploadBasePath
@Api("Application Info provider Controller")
public class FileApiInfoController {
	
	@Autowired
	private FileApiInfoService service;

	@ApiOperation(value = "Provides the information about the File Upload Services application",
			httpMethod = "GET", produces = "application/json", response = ResponseEntity.class,
			notes = "End point to provide information about File Upload Services application")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the App information"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/info")
	public ResponseEntity<AppInfo> getApiInfo() {
		return new ResponseEntity<AppInfo>(service.getApplicationInfo(), HttpStatus.OK);
	}
}

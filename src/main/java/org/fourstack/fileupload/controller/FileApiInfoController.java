package org.fourstack.fileupload.controller;

import org.fourstack.fileupload.config.FileUploadBasePath;
import org.fourstack.fileupload.entity.AppInfo;
import org.fourstack.fileupload.service.FileApiInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FileUploadBasePath
public class FileApiInfoController {
	
	@Autowired
	private FileApiInfoService service;

	@GetMapping("/info")
	public ResponseEntity<AppInfo> getApiInfo() {
		return new ResponseEntity<AppInfo>(service.getApplicationInfo(), HttpStatus.OK);
	}
}

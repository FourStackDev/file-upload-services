package org.fourstack.fileupload.service;

import java.util.ArrayList;
import java.util.List;

import org.fourstack.fileupload.codetype.MethodType;
import org.fourstack.fileupload.entity.AppInfo;
import org.fourstack.fileupload.entity.EndpointsDescription;
import org.fourstack.fileupload.property.FileApiInfoProperties;
import org.springframework.stereotype.Service;

@Service
public class FileApiInfoService {

	private String applicationName;
	private String applicationVersion;
	private String applicationDescription;

	public FileApiInfoService(FileApiInfoProperties properties) {
		this.applicationName = properties.getName();
		this.applicationVersion = properties.getVersion();
		this.applicationDescription = properties.getDescription();
	}

	public AppInfo getApplicationInfo() {
		AppInfo info = new AppInfo();
		info.set_appName(applicationName);
		info.set_appVersion(applicationVersion);
		info.set_appDescription(applicationDescription);
		info.set_endPoints(getEndpointsDetails());
		
		return info;
	}

	private List<EndpointsDescription> getEndpointsDetails() {
		List<EndpointsDescription> endPoints = new ArrayList<>();

		endPoints.add(new EndpointsDescription(MethodType.POST, "/file-uploads/api/files/uploadFiles/db",
				"Upload files to the database."));
		endPoints.add(new EndpointsDescription(MethodType.POST, "/file-uploads/api/files/uploadMultipleFiles/db",
				"Upload Multiple files to the database"));
		endPoints.add(new EndpointsDescription(MethodType.GET, "/file-uploads/api/files/downloadFiles/db/{fileName:.+}",
				"Get file from the database"));

		endPoints.add(new EndpointsDescription(MethodType.POST, "/file-uploads/api/files/uploadFiles/local",
				"Upload files to the Local System"));
		endPoints.add(new EndpointsDescription(MethodType.POST, "/file-uploads/api/files/uploadMultipleFiles/local",
				"Upload Multiple files to the Local System"));
		endPoints.add(
				new EndpointsDescription(MethodType.GET, "/file-uploads/api/files/downloadFiles/local/{fileName:.+}",
						"Download files from the local file System"));
		
		return endPoints;
	}
}

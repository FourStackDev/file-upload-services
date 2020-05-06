package org.fourstack.fileupload.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Class <b><i>FileStorageProperties</i></b> binds the application properties
 * which are prefixed with "file".<br/>
 * 
 * if additional properties to be defined then just add property prefixed with
 * "file" and provide corresponding fields.<br/>
 * Ex: if property name is file.download-dir, then field name is downloadDir
 * 
 * @author Manjunath_HM
 *
 */
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	private String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
}

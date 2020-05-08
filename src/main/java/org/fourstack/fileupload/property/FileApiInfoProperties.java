package org.fourstack.fileupload.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Class <b><i>FileApiInfoProperties</i></b> binds the application properties
 * which are prefixed with "app".
 * 
 * @author Manjunath_HM
 *
 */
@ConfigurationProperties(prefix = "app")
public class FileApiInfoProperties {
	private String name;
	private String version;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

package org.fourstack.fileupload.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1663587407093018638L;
	
	@JsonProperty(value = "application_name")
	private String _appName;

	@JsonProperty(value = "version")
	private String _appVersion;

	@JsonProperty(value = "description")
	private String _appDescription;

	@JsonProperty(value = "end_points_details")
	private List<EndpointsDescription> _endPoints;

	/**
	 * AppInfo default Constructor
	 */
	public AppInfo() {
	}

	/**
	 * Parameterized constructor to initialize the AppInfo Object without end points
	 * details
	 * 
	 * @param _appName
	 * @param _appVersion
	 * @param _appDescription
	 */
	public AppInfo(String _appName, String _appVersion, String _appDescription) {
		super();
		this._appName = _appName;
		this._appVersion = _appVersion;
		this._appDescription = _appDescription;
	}

	/**
	 * Parameterized constructor to initialize the AppInfo Object with end points
	 * details
	 * 
	 * @param _appName
	 * @param _appVersion
	 * @param _appDescription
	 * @param _endPoints
	 */
	public AppInfo(String _appName, String _appVersion, String _appDescription, List<EndpointsDescription> _endPoints) {
		super();
		this._appName = _appName;
		this._appVersion = _appVersion;
		this._appDescription = _appDescription;
		this._endPoints = _endPoints;
	}

	public String get_appName() {
		return _appName;
	}

	public void set_appName(String _appName) {
		this._appName = _appName;
	}

	public String get_appVersion() {
		return _appVersion;
	}

	public void set_appVersion(String _appVersion) {
		this._appVersion = _appVersion;
	}

	public String get_appDescription() {
		return _appDescription;
	}

	public void set_appDescription(String _appDescription) {
		this._appDescription = _appDescription;
	}

	public List<EndpointsDescription> get_endPoints() {
		return _endPoints;
	}

	public void set_endPoints(List<EndpointsDescription> _endPoints) {
		this._endPoints = _endPoints;
	}

}

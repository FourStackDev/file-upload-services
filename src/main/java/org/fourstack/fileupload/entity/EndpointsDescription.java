package org.fourstack.fileupload.entity;

import java.io.Serializable;

import org.fourstack.fileupload.codetype.MethodType;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EndpointsDescription implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9162206849330011782L;

	@JsonProperty(value = "method_type")
	private MethodType _method;
	
	@JsonProperty(value = "end_uri")
	private String _uri;
	
	@JsonProperty(value = "method_description")
	private String _description;

	/**
	 * No argument Constructor
	 */
	public EndpointsDescription() {
	}

	/**
	 * Parameterized Constructor to initialize the EndpointsDescription Object
	 * 
	 * @param _method
	 * @param _uri
	 * @param _description
	 */
	public EndpointsDescription(MethodType _method, String _uri, String _description) {
		super();
		this._method = _method;
		this._uri = _uri;
		this._description = _description;
	}

	public MethodType get_method() {
		return _method;
	}

	public void set_method(MethodType _method) {
		this._method = _method;
	}

	public String get_uri() {
		return _uri;
	}

	public void set_uri(String _uri) {
		this._uri = _uri;
	}

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}
}

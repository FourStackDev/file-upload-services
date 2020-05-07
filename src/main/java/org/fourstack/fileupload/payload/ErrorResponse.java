package org.fourstack.fileupload.payload;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.fourstack.fileupload.codetype.CustomErrorCodesAndMsgs;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;	

public class ErrorResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2886315007741365323L;

	@JsonProperty("custom_err_code")
	private CustomErrorCodesAndMsgs customErrorCode;

	@JsonProperty("custom_err_msg")
	private CustomErrorCodesAndMsgs customErrorMsg;

	@JsonProperty("custom_err_desc")
	private String customErrorDescription;

	@JsonProperty("error_code")
	private int errorCode;

	@JsonProperty("error_msg")
	private String errorMsg;

	@JsonProperty("http_status")
	private HttpStatus status;

	@JsonProperty("uri_details")
	private String urlDetails;

	@JsonProperty("timestamp")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss.SSS")
	private LocalDateTime timeStamp;

	public ErrorResponse() {
	}

	/**
	 * Parameterized Constructor to initialize the ErrorResponse Object
	 * 
	 * @param customErrorCode
	 * @param customErrorMsg
	 * @param customErrorDescription
	 * @param errorCode
	 * @param errorMsg
	 * @param status
	 * @param urlDetails
	 * @param timeStamp
	 */
	public ErrorResponse(CustomErrorCodesAndMsgs customErrorCode, CustomErrorCodesAndMsgs customErrorMsg,
			String customErrorDescription, int errorCode, String errorMsg, HttpStatus status, String urlDetails,
			LocalDateTime timeStamp) {
		this.customErrorCode = customErrorCode;
		this.customErrorMsg = customErrorMsg;
		this.customErrorDescription = customErrorDescription;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.status = status;
		this.urlDetails = urlDetails;
		this.timeStamp = timeStamp;
	}

	public CustomErrorCodesAndMsgs getCustomErrorCode() {
		return customErrorCode;
	}

	public void setCustomErrorCode(CustomErrorCodesAndMsgs customErrorCode) {
		this.customErrorCode = customErrorCode;
	}

	public CustomErrorCodesAndMsgs getCustomErrorMsg() {
		return customErrorMsg;
	}

	public void setCustomErrorMsg(CustomErrorCodesAndMsgs customErrorMsg) {
		this.customErrorMsg = customErrorMsg;
	}

	public String getCustomErrorDescription() {
		return customErrorDescription;
	}

	public void setCustomErrorDescription(String customErrorDescription) {
		this.customErrorDescription = customErrorDescription;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getUrlDetails() {
		return urlDetails;
	}

	public void setUrlDetails(String urlDetails) {
		this.urlDetails = urlDetails;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
}

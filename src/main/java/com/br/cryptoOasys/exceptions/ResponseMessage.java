package com.br.cryptoOasys.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseMessage {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", timezone = "GMT")
	private Instant timestamp;

	@JsonProperty(value = "statusCode")
	private int statusCode;

	@JsonProperty(value = "statusType")
	private HttpStatus statusType;

	private String message;

	@JsonProperty(value = "pathUri")
	private String path;	

	public ResponseMessage(Instant timestamp, int statusCode, HttpStatus statusType, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.statusCode = statusCode;
		this.statusType = statusType;
		this.message = message;
		this.path = path;
	}	
	
	public static ResponseMessage OK(String message, HttpServletRequest request) {		
		ResponseMessage response = new ResponseMessage();
		response.setMessage(message);		
		response.setStatusCode(HttpStatus.OK.value());
		response.setStatusType(HttpStatus.OK);
		response.setTimestamp(Instant.now());
		response.setPath(request.getRequestURI());
		return response;
	}	

	public ResponseMessage() {
		super();
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public HttpStatus getStatusType() {
		return statusType;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void setStatusType(HttpStatus statusType) {
		this.statusType = statusType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseErrorMessage [timestamp=" + timestamp + ", statusCode=" + statusCode + ", statusType="
				+ statusType + ", message=" + message + ", path=" + path + "]";
	}
}

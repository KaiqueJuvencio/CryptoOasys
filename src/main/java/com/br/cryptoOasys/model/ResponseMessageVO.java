package com.br.cryptoOasys.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Setter;

@Setter
public class ResponseMessageVO {
	@JsonProperty(value = "message")
	private String message;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", timezone = "GMT")
	private LocalDateTime time;
	@JsonProperty(value = "status")
	private String status;			
	
	public static ResponseMessageVO OK(String message) {		
		ResponseMessageVO response = new ResponseMessageVO();
		response.setMessage(message);		
		response.setStatus(HttpStatus.OK.toString());
		response.setTime(LocalDateTime.now());
		return response;
	}	
}

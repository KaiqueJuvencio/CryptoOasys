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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd - HH:mm", timezone = "GMT")
	private LocalDateTime time;
	@JsonProperty(value = "status")
	private String status;			
	
	public static ResponseMessageVO OK(String message) {		
		ResponseMessageVO n = new ResponseMessageVO();
		n.setMessage(message);		
		n.setStatus(HttpStatus.OK.toString());
		n.setTime(LocalDateTime.now());
		return n;
	}	
}

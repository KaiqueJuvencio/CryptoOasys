package com.br.cryptoOasys.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.br.cryptoOasys.exceptions.ResponseErrorMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Setter;

@Setter
public class ResponseSuccessMessageVO {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd - HH:mm", timezone = "GMT")
	private static LocalDateTime time;
	@JsonProperty(value = "code")
	private static int status;
	@JsonProperty(value = "type")
	private static HttpStatus error;
	@SuppressWarnings("unused")
	private static String message;
	
	public static ResponseErrorMessage OK(String message) {		
		ResponseErrorMessage r = new ResponseErrorMessage();
		r.setMessage("Teste");
		return r;
	}	
}

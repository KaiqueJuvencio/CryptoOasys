package com.br.cryptoOasys.exceptionhandler;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.cryptoOasys.exceptions.BadRequestException;
import com.br.cryptoOasys.exceptions.ResponseErrorMessage;
import com.br.cryptoOasys.exceptions.UserNotLoggedException;

@ControllerAdvice
public class ApiExceptionHandler {
	ResponseErrorMessage erro = new ResponseErrorMessage();

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ResponseErrorMessage> handleBadRequestException(BadRequestException badRequestException,
			HttpServletRequest request) {
		erro.setError(HttpStatus.BAD_REQUEST);
		erro.setPath(request.getRequestURI());
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setTimestamp(Instant.now());
		erro.setMessage(badRequestException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(UserNotLoggedException.class)
	public ResponseEntity<ResponseErrorMessage> handleAccountNotFoundException(UserNotLoggedException accountNotFoundException,
			HttpServletRequest request) {
		erro.setError(HttpStatus.BAD_REQUEST);
		erro.setPath(request.getRequestURI());
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setTimestamp(Instant.now());
		erro.setMessage(accountNotFoundException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}

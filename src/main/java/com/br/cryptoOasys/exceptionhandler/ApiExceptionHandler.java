package com.br.cryptoOasys.exceptionhandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.cryptoOasys.exceptions.BadRequestException;
import com.br.cryptoOasys.exceptions.FavoritesDontExistException;
import com.br.cryptoOasys.exceptions.ResponseErrorMessage;
import com.br.cryptoOasys.exceptions.UserNotLoggedException;
import com.br.cryptoOasys.model.FieldNotValid;
import com.br.cryptoOasys.model.ResponseErrorMessageNotValid;
import com.br.cryptoOasys.model.ResponseMessageVO;

@ControllerAdvice
public class ApiExceptionHandler {
	ResponseErrorMessage erro = new ResponseErrorMessage();
	ResponseMessageVO messageVO = new ResponseMessageVO();
	
	@Autowired
	private MessageSource messageSource;


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
	
	@ExceptionHandler(FavoritesDontExistException.class)
	public ResponseEntity<ResponseMessageVO> handleAccountNotFoundException(FavoritesDontExistException accountNotFoundException,
			HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseMessageVO.OK("There are no favorite coins"));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseErrorMessageNotValid> invalidForm(MethodArgumentNotValidException exception, HttpServletRequest request) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		ResponseErrorMessageNotValid erro = new ResponseErrorMessageNotValid();
		erro.setError(HttpStatus.BAD_REQUEST);
		erro.setPath(request.getRequestURI());
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setTimestamp(Instant.now());
		erro.setMessage("Campos não inseridos corretamente");
		List<FieldNotValid> fieldsNotValidList = new ArrayList<FieldNotValid>();
		fieldErrors.forEach(error ->{
			String field = error.getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			FieldNotValid fieldNotValid = new FieldNotValid(field, message); 
			fieldsNotValidList.add(fieldNotValid);					
		});
		erro.setFields(fieldsNotValidList);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}

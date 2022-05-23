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
import com.br.cryptoOasys.exceptions.ObjectNullException;
import com.br.cryptoOasys.exceptions.ResponseMessage;
import com.br.cryptoOasys.exceptions.UserNotLoggedException;
import com.br.cryptoOasys.model.FieldNotValid;
import com.br.cryptoOasys.model.ResponseErrorMessageNotValid;
import com.br.cryptoOasys.model.ResponseMessageVO;

@ControllerAdvice
public class ApiExceptionHandler {
	ResponseMessage response = new ResponseMessage();
	ResponseMessageVO messageVO = new ResponseMessageVO();
	
	@Autowired
	private MessageSource messageSource;


	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ResponseMessage> handleBadRequestException(BadRequestException badRequestException,
			HttpServletRequest request) {
		response.setStatusType(HttpStatus.BAD_REQUEST);
		response.setPath(request.getRequestURI());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setTimestamp(Instant.now());
		response.setMessage(badRequestException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	@ExceptionHandler(UserNotLoggedException.class)
	public ResponseEntity<ResponseMessage> handleAccountNotFoundException(UserNotLoggedException accountNotFoundException,
			HttpServletRequest request) {
		response.setStatusType(HttpStatus.BAD_REQUEST);
		response.setPath(request.getRequestURI());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setTimestamp(Instant.now());
		response.setMessage(accountNotFoundException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
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
		erro.setStatusType(HttpStatus.BAD_REQUEST);
		erro.setPath(request.getRequestURI());
		erro.setStatusCode(HttpStatus.BAD_REQUEST.value());
		erro.setTimestamp(Instant.now());
		erro.setMessage("Fields not entered correctly");
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
	
	@ExceptionHandler(ObjectNullException.class)
	public ResponseEntity<ResponseMessage> handleObjectNullException(ObjectNullException objectNullException,
			HttpServletRequest request) {
		response.setStatusType(HttpStatus.ACCEPTED);
		response.setPath(request.getRequestURI());
		response.setStatusCode(HttpStatus.ACCEPTED.value());
		response.setTimestamp(Instant.now());
		response.setMessage(objectNullException.getMessage());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}
}

package com.br.cryptoOasys.exceptions;

public class UserNotLoggedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public UserNotLoggedException(String message) {
		super(message);
	}
}

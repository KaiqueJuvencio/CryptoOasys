package com.br.cryptoOasys.exceptions;

public class ObjectNullException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNullException(String message) {
		super(message);
	}
}

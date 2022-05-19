package com.br.cryptoOasys.exceptions;

public class FavoritesDontExistException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public FavoritesDontExistException(String message) {
		super(message);
	}
}

package com.br.cryptoOasys.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CoinVO{	
	private String id;
	private String name;
	private String symbol;	
	private boolean favorite;
	@JsonInclude(Include.NON_NULL)
	private FavoriteCoinDTO coinFavorite;
	
	public CoinVO() {
		super();
	}

	public CoinVO(String id, String name, String symbol, boolean favorite, FavoriteCoinDTO coinFavorite) {
		super();
		this.id = id;
		this.name = name;
		this.symbol = symbol;
		this.favorite = favorite;
		this.coinFavorite = coinFavorite;
	}		
}

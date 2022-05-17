package com.br.cryptoOasys.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CoinDTO{	
	private String id;
	private String name;
	private String symbol;	
	private boolean favorite;
	private CoinFavoritedDTO coinFavorite;
	
	public CoinDTO() {
		super();
	}

	public CoinDTO(String id, String name, String symbol, boolean favorite, CoinFavoritedDTO coinFavorite) {
		super();
		this.id = id;
		this.name = name;
		this.symbol = symbol;
		this.favorite = favorite;
		this.coinFavorite = coinFavorite;
	}		
}

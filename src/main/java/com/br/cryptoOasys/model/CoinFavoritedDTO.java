package com.br.cryptoOasys.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class CoinFavoritedDTO extends CoinDTO{
	private String coinId;
	private String notes;
	private String created;
	private String updates;
}

package com.br.cryptoOasys.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name="TB_COIN_FAVORITE")
@Data
public class CoinFavoritedDTO{
	@Id
	private String id;
	private String name;
	private String symbol;	
	private String notes; 
	private String userId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", locale = "pt-BR", timezone = "Brazil/East")
	private LocalDateTime created;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", locale = "pt-BR", timezone = "Brazil/East")
	private LocalDateTime updated;
	
	public CoinFavoritedDTO() {
		super();
	}

	public CoinFavoritedDTO(String id, String name, String symbol, String notes, String userId,
			LocalDateTime created, LocalDateTime updated) {
		super();
		this.id = id;
		this.name = name;
		this.symbol = symbol;
		this.notes = notes;
		this.userId = userId;		
		this.created = created;
		this.updated = updated;
	}
}

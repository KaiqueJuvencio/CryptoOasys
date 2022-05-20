package com.br.cryptoOasys.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Entity
@Table(name="TB_FAVORITE_COIN")
@Data
public class FavoriteCoinDTO{
	@NotNull
	@Id
	private String id;
	private String name;
	private String symbol;
	@JsonInclude(Include.NON_NULL)
	private String notes;
	@JsonIgnore
	private String userId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", locale = "pt-BR", timezone = "Brazil/East")
	private LocalDateTime created;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", locale = "pt-BR", timezone = "Brazil/East")
	private LocalDateTime updated;
	
	public FavoriteCoinDTO() {
		super();
	}

	public FavoriteCoinDTO(String id, String name, String symbol, String notes, String userId,
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

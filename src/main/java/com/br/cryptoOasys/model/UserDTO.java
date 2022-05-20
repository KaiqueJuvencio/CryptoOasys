package com.br.cryptoOasys.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="TB_USER")
@Data
@Builder
public class UserDTO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	@NotNull
	private String nickName;
	@NotNull
	private String password;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(Long id, String name, String nickName, String password) {
		super();
		this.id = id;
		this.name = name;
		this.nickName = nickName;
		this.password = password;
	}			
}

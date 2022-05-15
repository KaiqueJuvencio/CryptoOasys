package com.br.cryptoOasys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cryptoOasys.model.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long>{
	
}
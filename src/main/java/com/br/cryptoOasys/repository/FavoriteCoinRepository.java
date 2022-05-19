package com.br.cryptoOasys.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cryptoOasys.model.FavoriteCoinDTO;

@Repository
public interface FavoriteCoinRepository extends JpaRepository<FavoriteCoinDTO, String>{
	List<FavoriteCoinDTO> findByUserId(String userId);
	Optional<FavoriteCoinDTO> findByUserIdAndId(String userId, String id);
}
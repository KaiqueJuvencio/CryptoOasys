package com.br.cryptoOasys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cryptoOasys.model.CoinFavoritedDTO;

@Repository
public interface CoinFavoritedRepository extends JpaRepository<CoinFavoritedDTO, String>{
	List<CoinFavoritedDTO> findByUserId(String userId);
}
package com.br.cryptoOasys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.cryptoOasys.model.CoinFavoritedDTO;

@Repository
public interface CoinRepository extends JpaRepository<CoinFavoritedDTO, String>{
}
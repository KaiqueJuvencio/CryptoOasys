package com.br.cryptoOasys.service;

import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.br.cryptoOasys.model.CoinDTO;

@FeignClient(name = "coinpaprika", url = "https://api.coinpaprika.com/v1/")
public interface FeignRequest {
	@GetMapping(value = "coins", produces = "application/json")
	public ResponseEntity<ArrayList<CoinDTO>> listCoins();
	
	@GetMapping(value = "coins/{coinId}", produces = "application/json")
	public ResponseEntity<CoinDTO> coinById(@PathVariable("coinId") String coinId);
}

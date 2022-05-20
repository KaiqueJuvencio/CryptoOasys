package com.br.cryptoOasys.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.br.cryptoOasys.model.CoinVO;

@FeignClient(name = "coinpaprika", url = "https://api.coinpaprika.com/v1/")
public interface FeignRequest {
	@GetMapping(value = "coins", produces = "application/json")
	@Cacheable(value ="coin")
	public ResponseEntity<List<CoinVO>> listCoins();
	
	@GetMapping(value = "coins/{coinId}", produces = "application/json")
	public ResponseEntity<CoinVO> coinById(@PathVariable("coinId") String coinId);
}

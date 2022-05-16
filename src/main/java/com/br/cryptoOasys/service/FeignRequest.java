package com.br.cryptoOasys.service;

import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.br.cryptoOasys.model.CoinDTO;

@FeignClient(name = "coinpaprika", url = "https://api.coinpaprika.com/v1/")
public interface FeignRequest {
	@GetMapping(value = "coins", produces = "application/json")
	public ResponseEntity<ArrayList<CoinDTO>> listCoins();
//	public ResponseEntity<CoinDTO> listCoins(@PathVariable("youtubeChannelId") String youtubeChannelId);
}

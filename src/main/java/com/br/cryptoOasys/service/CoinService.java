package com.br.cryptoOasys.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.cryptoOasys.exceptions.BadRequestException;
import com.br.cryptoOasys.model.CoinDTO;

@Service
public class CoinService {
	
	@Autowired(required=true)
	FeignRequest feignRequest;
	
	public ResponseEntity<ArrayList<CoinDTO>> list() {
		try {
			ResponseEntity<ArrayList<CoinDTO>> coins = feignRequest.listCoins();
			return coins;
		}catch(Exception error) {
			throw new BadRequestException("Error to register user");
		}		
	}
}

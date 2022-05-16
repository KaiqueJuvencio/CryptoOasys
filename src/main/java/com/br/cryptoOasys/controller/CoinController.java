package com.br.cryptoOasys.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.cryptoOasys.model.CoinDTO;
import com.br.cryptoOasys.service.CoinService;

@RestController
@RequestMapping("/api/coin")
public class CoinController {
	
	@Autowired
	CoinService coinService;
	
	@GetMapping
	public ResponseEntity<ArrayList<CoinDTO>> list() {		
		return coinService.list();
	}
}

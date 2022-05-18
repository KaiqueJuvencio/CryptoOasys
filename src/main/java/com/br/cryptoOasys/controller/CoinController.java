package com.br.cryptoOasys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.cryptoOasys.model.CoinDTO;
import com.br.cryptoOasys.model.CoinFavoritedDTO;
import com.br.cryptoOasys.service.CoinService;

@RestController
@RequestMapping("/api/coin")
public class CoinController {
	
	@Autowired
	CoinService coinService;
	
	@GetMapping
	public ResponseEntity<List<CoinDTO>> listCoins(HttpServletRequest request, HttpServletResponse response) {		
		return coinService.list(request, response);
	}
	
	@GetMapping("/favorites")
	public List<CoinFavoritedDTO> listFavoriteCoins(HttpServletRequest request, HttpServletResponse response) {		
		return coinService.findFavoritesByUserId(request, response);
	}
	
	@PostMapping("/favoriting")
	public CoinFavoritedDTO favoriting(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam String notes, @RequestParam String coinId) {		
		return coinService.favoriting(request, response, coinId, notes);
	}
}

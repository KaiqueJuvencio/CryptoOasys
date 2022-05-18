package com.br.cryptoOasys.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.cryptoOasys.exceptions.BadRequestException;
import com.br.cryptoOasys.model.CoinDTO;
import com.br.cryptoOasys.model.FavoriteCoinDTO;
import com.br.cryptoOasys.repository.FavoriteCoinRepository;

@Service
public class CoinService {

	@Autowired(required = true)
	FeignRequest feignRequest;

	@Autowired
	FavoriteCoinRepository coinFavoriteRepository;
	
	@Autowired
	FavoriteCoinService favoriteCoinService;

	public ResponseEntity<List<CoinDTO>> list(HttpServletRequest request, HttpServletResponse response) {	
		try {
			ResponseEntity<List<CoinDTO>> coins = feignRequest.listCoins();
			List<FavoriteCoinDTO> coinsFavorited = favoriteCoinService.findFavoritesByUserId(request, response);
			coins.getBody().forEach(c -> {
				coinsFavorited.forEach(c2 -> {
					if (c2.getId().equals(c.getId())) {
						c.setCoinFavorite(c2);
						c.setFavorite(true);
					}
				});
			});
			return coins;
		} catch (Exception error) {
			throw new BadRequestException("Error to register user");
		}
	}
}

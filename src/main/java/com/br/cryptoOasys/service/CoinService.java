package com.br.cryptoOasys.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.cryptoOasys.exceptions.BadRequestException;
import com.br.cryptoOasys.model.CoinDTO;
import com.br.cryptoOasys.model.CoinFavoritedDTO;
import com.br.cryptoOasys.repository.CoinRepository;

@Service
public class CoinService {

	@Autowired(required = true)
	FeignRequest feignRequest;

	@Autowired
	CoinRepository coinRepository;

	public ResponseEntity<ArrayList<CoinDTO>> list() {
		try {
			ResponseEntity<ArrayList<CoinDTO>> coins = feignRequest.listCoins();
			return coins;
		} catch (Exception error) {
			throw new BadRequestException("Error to register user");
		}
	}

	public CoinFavoritedDTO favoriting(HttpServletRequest request, HttpServletResponse response, String coinId, String notes) {
		HttpSession session = request.getSession();

		CoinDTO coin = feignRequest.coinById(coinId).getBody();
		CoinFavoritedDTO coinFavorite = new CoinFavoritedDTO();
			coinFavorite.setId(coin.getId());
			coinFavorite.setName(coin.getName());
			coinFavorite.setSymbol(coin.getSymbol());
			coinFavorite.setFavorite(true);
			coinFavorite.setUserId(session.getAttribute("userLogged").toString());
			coinFavorite.setNotes(notes);
			coinFavorite.setCreated(LocalDateTime.now());
			coinFavorite.setUpdated(LocalDateTime.now());
		return coinRepository.save(coinFavorite);
	}
}

package com.br.cryptoOasys.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cryptoOasys.model.CoinDTO;
import com.br.cryptoOasys.model.FavoriteCoinDTO;
import com.br.cryptoOasys.repository.FavoriteCoinRepository;

@Service
public class FavoriteCoinService {

	@Autowired(required = true)
	FeignRequest feignRequest;

	@Autowired
	FavoriteCoinRepository coinFavoriteRepository;

	public FavoriteCoinDTO favoriting(HttpServletRequest request, HttpServletResponse response, String coinId,
			String notes) {
		String userIdLogged = this.getLoggedUser(request);
		CoinDTO coin = feignRequest.coinById(coinId).getBody();
		FavoriteCoinDTO coinFavorite = new FavoriteCoinDTO();
		coinFavorite.setId(coin.getId());
		coinFavorite.setName(coin.getName());
		coinFavorite.setSymbol(coin.getSymbol());
		coinFavorite.setUserId(userIdLogged);
		coinFavorite.setNotes(notes);
		coinFavorite.setCreated(LocalDateTime.now());
		coinFavorite.setUpdated(LocalDateTime.now());
		return coinFavoriteRepository.save(coinFavorite);
	}

	public List<FavoriteCoinDTO> findFavoritesByUserId(HttpServletRequest request, HttpServletResponse response) {
		String userIdLogged = this.getLoggedUser(request);
		return coinFavoriteRepository.findByUserId(userIdLogged);
	}
	
	public FavoriteCoinDTO delete(HttpServletRequest request, HttpServletResponse response, String coinId) {				
		String userIdLogged = this.getLoggedUser(request);
		FavoriteCoinDTO favoriteCoin = coinFavoriteRepository.findByUserIdAndId(userIdLogged, coinId);
		coinFavoriteRepository.delete(favoriteCoin);
		return favoriteCoin;
	}
	
	public String getLoggedUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return session.getAttribute("userLogged").toString();
	}
}

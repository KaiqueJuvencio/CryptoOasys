package com.br.cryptoOasys.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.cryptoOasys.exceptions.BadRequestException;
import com.br.cryptoOasys.model.CoinDTO;
import com.br.cryptoOasys.model.CoinFavoritedDTO;
import com.br.cryptoOasys.repository.CoinFavoritedRepository;

@Service
public class CoinService {

	@Autowired(required = true)
	FeignRequest feignRequest;

	@Autowired
	CoinFavoritedRepository coinFavoriteRepository;

	public ResponseEntity<List<CoinDTO>> list(HttpServletRequest request, HttpServletResponse response) {	
		try {
			ResponseEntity<List<CoinDTO>> coins = feignRequest.listCoins();
			List<CoinFavoritedDTO> coinsFavorited = findFavoritesByUserId(request, response);
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

	public CoinFavoritedDTO favoriting(HttpServletRequest request, HttpServletResponse response, String coinId,
			String notes) {
		HttpSession session = request.getSession();
		CoinDTO coin = feignRequest.coinById(coinId).getBody();
		CoinFavoritedDTO coinFavorite = new CoinFavoritedDTO();
		coinFavorite.setId(coin.getId());
		coinFavorite.setName(coin.getName());
		coinFavorite.setSymbol(coin.getSymbol());
		coinFavorite.setUserId(session.getAttribute("userLogged").toString());
		coinFavorite.setNotes(notes);
		coinFavorite.setCreated(LocalDateTime.now());
		coinFavorite.setUpdated(LocalDateTime.now());
		return coinFavoriteRepository.save(coinFavorite);
	}

	public List<CoinFavoritedDTO> findFavoritesByUserId(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String userIdLogged = session.getAttribute("userLogged").toString();
		return coinFavoriteRepository.findByUserId(userIdLogged);
	}	
}

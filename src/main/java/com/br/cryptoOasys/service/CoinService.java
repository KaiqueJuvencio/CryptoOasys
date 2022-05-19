package com.br.cryptoOasys.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.cryptoOasys.exceptions.BadRequestException;
import com.br.cryptoOasys.model.CoinVO;
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
	
	@Autowired
	UserService userService;

	public ResponseEntity<List<CoinVO>> list(HttpServletRequest request, HttpServletResponse response) {
		userService.verifyIfUserIsLogged(request);
		String userIdLogged = userService.getLoggedUser(request);
		try {
			ResponseEntity<List<CoinVO>> coins = feignRequest.listCoins();
			List<FavoriteCoinDTO> favoriteCoins = coinFavoriteRepository.findByUserId(userIdLogged);
			if(!coins.getBody().isEmpty() && !favoriteCoins.isEmpty()) {
				coins.getBody().forEach(c -> {
					favoriteCoins.forEach(c2 -> {
						if (c2.getId().equals(c.getId())) {
							c.setCoinFavorite(c2);
							c.setFavorite(true);
						}
					});
				});
			}			
			return coins;
		} catch (Exception error) {
			throw new BadRequestException("Error to list coins");
		}
	}
}

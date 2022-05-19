package com.br.cryptoOasys.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cryptoOasys.exceptions.BadRequestException;
import com.br.cryptoOasys.exceptions.FavoritesDontExistException;
import com.br.cryptoOasys.model.CoinVO;
import com.br.cryptoOasys.model.FavoriteCoinDTO;
import com.br.cryptoOasys.repository.FavoriteCoinRepository;

@Service
public class FavoriteCoinService {

	@Autowired(required = true)
	FeignRequest feignRequest;

	@Autowired
	FavoriteCoinRepository coinFavoriteRepository;

	@Autowired
	UserService userService;

	public FavoriteCoinDTO favoriting(HttpServletRequest request, HttpServletResponse response, String coinId,
			String notes) {
		String errorMessage = "Error to favoriting coin. Make sure this coin exist";
		userService.verifyIfUserIsLogged(request);
		try {
			String userIdLogged = userService.getLoggedUser(request);
			CoinVO coin = feignRequest.coinById(coinId).getBody();
			FavoriteCoinDTO coinFavorite = new FavoriteCoinDTO();
			coinFavorite.setId(coin.getId());
			coinFavorite.setName(coin.getName());
			coinFavorite.setSymbol(coin.getSymbol());
			coinFavorite.setUserId(userIdLogged);
			coinFavorite.setNotes(notes);
			coinFavorite.setCreated(LocalDateTime.now());
			coinFavorite.setUpdated(LocalDateTime.now());
			return coinFavoriteRepository.save(coinFavorite);
		} catch (Exception e) {
			throw new BadRequestException(errorMessage);
		}
	}

	public List<FavoriteCoinDTO> findFavoritesByUserId(HttpServletRequest request, HttpServletResponse response) {
		String errorMessage = "Error";
		try {
			String userIdLogged = userService.getLoggedUser(request);
			List<FavoriteCoinDTO> favoriteCoins = coinFavoriteRepository.findByUserId(userIdLogged);
			errorMessage = favoriteCoins.isEmpty() ? "There are no favorite coins" : "Error to find favorite coins";
			this.verifyFavoriteCoinsIsNull(favoriteCoins);
			return favoriteCoins;
		} catch (Exception e) {
			throw new BadRequestException(errorMessage);
		}
	}

	public FavoriteCoinDTO update(HttpServletRequest request, HttpServletResponse response, String coinId,
			String notes) {
		String errorMessage = "Error";
		userService.verifyIfUserIsLogged(request);
		try {
			String userIdLogged = userService.getLoggedUser(request);
			Optional<FavoriteCoinDTO> favoriteCoin = coinFavoriteRepository.findByUserIdAndId(userIdLogged, coinId);
			errorMessage = favoriteCoin.isEmpty() ? "This coin is not a favorite" : "Error to update notes";
			favoriteCoin.get().setNotes(notes);
			favoriteCoin.get().setUpdated(LocalDateTime.now());
			coinFavoriteRepository.save(favoriteCoin.get());
			return favoriteCoin.get();
		} catch (Exception e) {
			throw new BadRequestException(errorMessage);
		}
	}

	public FavoriteCoinDTO delete(HttpServletRequest request, HttpServletResponse response, String coinId)
			throws FavoritesDontExistException {
		String errorMessage = "Error";
		userService.verifyIfUserIsLogged(request);
		try {
			String userIdLogged = userService.getLoggedUser(request);
			Optional<FavoriteCoinDTO> favoriteCoin = coinFavoriteRepository.findByUserIdAndId(userIdLogged, coinId);
			errorMessage = favoriteCoin.isEmpty() ? "This coin not exist" : "Error to delete coin";
			coinFavoriteRepository.delete(favoriteCoin.get());
			return favoriteCoin.get();
		} catch (Exception e) {
			throw new BadRequestException(errorMessage);
		}
	}	
	
	public void verifyFavoriteCoinsIsNull(List<FavoriteCoinDTO> favoriteCoins) {
		if(favoriteCoins.isEmpty()) {			
			throw new BadRequestException("");
		}
	}
}

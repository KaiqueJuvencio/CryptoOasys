package com.br.cryptoOasys.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cryptoOasys.exceptions.BadRequestException;
import com.br.cryptoOasys.exceptions.FavoritesDontExistException;
import com.br.cryptoOasys.exceptions.ObjectNullException;
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
	RedisService redisService;

	@Autowired
	UserService userService;

	public FavoriteCoinDTO favoriting(String coinId, String notes) {
		userService.verifyIfUserIsLogged();
		String errorMessage = "Error to favoriting coin. Make sure this coin exist";
		try {
			String userIdLogged = redisService.getUserLogged();
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

	public List<FavoriteCoinDTO> findFavoritesByUserId() {
		userService.verifyIfUserIsLogged();			
		try {
			String userIdLogged = redisService.getUserLogged();
			List<FavoriteCoinDTO> favoriteCoins = coinFavoriteRepository.findByUserId(userIdLogged);			
			this.verifyFavoriteCoinsIsNull(favoriteCoins);
			return favoriteCoins;
		} catch (ObjectNullException  e) {
			throw new ObjectNullException("There are no favorite coins");			
		} catch (Exception e) {
			throw new BadRequestException("Error to find favorite coins");
		}
	}

	public FavoriteCoinDTO update(String coinId, String notes) {
		userService.verifyIfUserIsLogged();		
		try {
			String userIdLogged = redisService.getUserLogged();
			Optional<FavoriteCoinDTO> favoriteCoin = coinFavoriteRepository.findByUserIdAndId(userIdLogged, coinId);
			this.verifyFavoriteCoinsIsNull(favoriteCoin);
			favoriteCoin.get().setNotes(notes);
			favoriteCoin.get().setUpdated(LocalDateTime.now());
			coinFavoriteRepository.save(favoriteCoin.get());
			return favoriteCoin.get();
		} catch (ObjectNullException  e) {
			throw new ObjectNullException("This coin is not a favorite");			
		} catch (Exception e) {
			throw new BadRequestException("Error to update notes");
		}
	}

	public FavoriteCoinDTO delete(String coinId)
			throws FavoritesDontExistException {
		userService.verifyIfUserIsLogged();		
		try {
			String userIdLogged = redisService.getUserLogged();
			Optional<FavoriteCoinDTO> favoriteCoin = coinFavoriteRepository.findByUserIdAndId(userIdLogged, coinId);
			this.verifyFavoriteCoinsIsNull(favoriteCoin);
			coinFavoriteRepository.delete(favoriteCoin.get());
			return favoriteCoin.get();
		} catch (ObjectNullException  e) {
			throw new ObjectNullException("This coin not exist");			
		} catch (Exception e) {
			throw new BadRequestException("Error to delete coin");
		}
	}	
	
	public void verifyFavoriteCoinsIsNull(List<FavoriteCoinDTO> favoriteCoins) {
		if(favoriteCoins.isEmpty()) throw new ObjectNullException("");
	}
	
	public void verifyFavoriteCoinsIsNull(Optional<FavoriteCoinDTO> favoriteCoins) {
		if(favoriteCoins.isEmpty()) throw new ObjectNullException("");
	}
}

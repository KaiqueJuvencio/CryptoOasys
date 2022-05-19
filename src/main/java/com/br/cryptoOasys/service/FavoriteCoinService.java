package com.br.cryptoOasys.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cryptoOasys.exceptions.BadRequestException;
import com.br.cryptoOasys.exceptions.UserNotLoggedException;
import com.br.cryptoOasys.model.CoinDTO;
import com.br.cryptoOasys.model.FavoriteCoinDTO;
import com.br.cryptoOasys.repository.FavoriteCoinRepository;

@Service
public class FavoriteCoinService {

	@Autowired(required = true)
	FeignRequest feignRequest;

	@Autowired
	FavoriteCoinRepository coinFavoriteRepository;
	
	String errorMessage = "Error";

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
	
	public FavoriteCoinDTO update(HttpServletRequest request, HttpServletResponse response, String coinId, String notes) {				
		this.verifyIfUserIsLogged(request);
		try {
			String userIdLogged = this.getLoggedUser(request);
			Optional<FavoriteCoinDTO> favoriteCoin = coinFavoriteRepository.findByUserIdAndId(userIdLogged, coinId);
			errorMessage = favoriteCoin.isEmpty() ? "This coin is not a favorite" : "Error to update notes";			
			favoriteCoin.get().setNotes(notes);
			favoriteCoin.get().setUpdated(LocalDateTime.now());
			coinFavoriteRepository.save(favoriteCoin.get());	
			return favoriteCoin.get();
		}catch (Exception e) {
			throw new BadRequestException(errorMessage);
		}			
	}
	
	public FavoriteCoinDTO delete(HttpServletRequest request, HttpServletResponse response, String coinId) {						
		String userIdLogged = this.getLoggedUser(request);
		Optional<FavoriteCoinDTO> favoriteCoin = coinFavoriteRepository.findByUserIdAndId(userIdLogged, coinId);
		coinFavoriteRepository.delete(favoriteCoin.get());
		return favoriteCoin.get();
	}
	
	public String getLoggedUser(HttpServletRequest request) {
		this.verifyIfUserIsLogged(request);
		HttpSession session = request.getSession();			
		return session.getAttribute("userLogged").toString(); 
	}
	
	public void verifyIfUserIsLogged(HttpServletRequest request)  {		
		HttpSession session = request.getSession();		
		if(session.getAttribute("userLogged") == null) {
			throw new UserNotLoggedException("User not logged");
		}															
	}
}

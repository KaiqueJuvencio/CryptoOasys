package com.br.cryptoOasys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.cryptoOasys.exceptions.ResponseMessage;
import com.br.cryptoOasys.model.FavoriteCoinDTO;
import com.br.cryptoOasys.service.FavoriteCoinService;

@RestController
@RequestMapping("/api/favorite-coin")
public class FavoriteCoinController {

	@Autowired
	FavoriteCoinService favoriteCoinService;

	@GetMapping
	public ResponseEntity<List<FavoriteCoinDTO>> list(HttpServletRequest request, HttpServletResponse response) {
		List<FavoriteCoinDTO> favoriteCoins = favoriteCoinService.findFavoritesByUserId(request, response);
		return ResponseEntity.ok(favoriteCoins);
	}

	@PostMapping
	public ResponseEntity<ResponseMessage> favoriting(HttpServletRequest request, HttpServletResponse response,
			@RequestBody @Valid FavoriteCoinDTO favoriteCoin) {
		favoriteCoinService.favoriting(request, response, favoriteCoin.getId(), favoriteCoin.getNotes());
		return ResponseEntity.ok(ResponseMessage.OK("Successfully favorited", request));
	}
	
	@PutMapping
	public ResponseEntity<ResponseMessage> update(HttpServletRequest request, HttpServletResponse response,
			@RequestBody @Valid FavoriteCoinDTO favoriteCoin) {
		favoriteCoinService.update(request, response, favoriteCoin.getId(), favoriteCoin.getNotes());
		return ResponseEntity.ok(ResponseMessage.OK("Notes successfully updated", request));
	}
	
	@CachePut(value = "coin")
	@DeleteMapping("/{coinId}")
	public ResponseEntity<ResponseMessage> delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String coinId) {
		favoriteCoinService.delete(request, response, coinId);
		return ResponseEntity.ok(ResponseMessage.OK("Favorite coin successfully deleted", request));
	}
}

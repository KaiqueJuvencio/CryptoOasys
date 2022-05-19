package com.br.cryptoOasys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.cryptoOasys.model.FavoriteCoinDTO;
import com.br.cryptoOasys.model.ResponseMessageVO;
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
	public ResponseEntity<ResponseMessageVO> favoriting(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String notes, @RequestParam String coinId) {
		favoriteCoinService.favoriting(request, response, coinId, notes);
		return ResponseEntity.ok(ResponseMessageVO.OK("Successfully favorited"));
	}

	@PutMapping
	public ResponseEntity<ResponseMessageVO> update(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String notes, @RequestParam String coinId) {
		favoriteCoinService.update(request, response, coinId, notes);
		return ResponseEntity.ok(ResponseMessageVO.OK("Notes successfully updated"));
	}

	@DeleteMapping("/{coinId}")
	public ResponseEntity<ResponseMessageVO> delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String coinId) {
		favoriteCoinService.delete(request, response, coinId);
		return ResponseEntity.ok(ResponseMessageVO.OK("Favorite coin successfully deleted"));
	}
}

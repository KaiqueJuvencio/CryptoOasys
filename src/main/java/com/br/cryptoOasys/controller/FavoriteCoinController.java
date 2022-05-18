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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.cryptoOasys.model.FavoriteCoinDTO;
import com.br.cryptoOasys.service.FavoriteCoinService;

@RestController
@RequestMapping("/api/favorite-coin")
public class FavoriteCoinController {
	
	@Autowired
	FavoriteCoinService favoriteCoinService;
	
	@GetMapping
	public List<FavoriteCoinDTO> list(HttpServletRequest request, HttpServletResponse response) {		
		return favoriteCoinService.findFavoritesByUserId(request, response);
	}
	
	@PostMapping
	public FavoriteCoinDTO favoriting(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam String notes, @RequestParam String coinId) {		
		return favoriteCoinService.favoriting(request, response, coinId, notes);
	}
	
	@DeleteMapping("/{coinId}")
	public ResponseEntity<FavoriteCoinDTO> delete(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable String coinId) {		
		return ResponseEntity.ok(favoriteCoinService.delete(request, response, coinId));
	}
}

package com.br.cryptoOasys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.cryptoOasys.model.UserDTO;
import com.br.cryptoOasys.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserDTO> register(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String name, @RequestParam String nickName, @RequestParam String password) {		
		UserDTO user = userService.register(request, name, nickName, password);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/login/{nickname}/{password}")
	public Object login(HttpServletRequest request, HttpServletResponse response,
						@PathVariable String nickname, @PathVariable String password) {
		return userService.login(request, response, nickname, password);
	}
}
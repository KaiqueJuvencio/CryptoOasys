package com.br.cryptoOasys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.cryptoOasys.exceptions.ResponseMessage;
import com.br.cryptoOasys.model.UserDTO;
import com.br.cryptoOasys.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<ResponseMessage> register(HttpServletRequest request, @RequestBody @Valid UserDTO user) {		
		UserDTO userCreated = userService.register(user.getName(), user.getNickName(), user.getPassword());
		return ResponseEntity.ok(ResponseMessage.OK(userCreated.getName() + " successfully created", request));
	}
	
	@GetMapping("/login")
	public Object login(HttpServletRequest request, @RequestBody @Valid UserDTO user) {
		String userLogged = userService.login(user.getNickName(), user.getPassword());
		return ResponseEntity.ok(ResponseMessage.OK(userLogged + " successfully logged", request));
	}
	
	@GetMapping("/logout")
	public Object logout(HttpServletRequest request) {
		userService.logout();
		return ResponseEntity.ok(ResponseMessage.OK("Logout succesful", request));
	}
}
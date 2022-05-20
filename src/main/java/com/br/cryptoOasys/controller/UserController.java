package com.br.cryptoOasys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.cryptoOasys.model.ResponseMessageVO;
import com.br.cryptoOasys.model.UserDTO;
import com.br.cryptoOasys.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<ResponseMessageVO> register(HttpServletRequest request, HttpServletResponse response,
			@RequestBody @Valid UserDTO user) {		
		UserDTO userCreated = userService.register(request, user.getName(), user.getNickName(), user.getPassword());
		return ResponseEntity.ok(ResponseMessageVO.OK(userCreated.getName() + " successfully created"));
	}
	
	@GetMapping("/login")
	public Object login(HttpServletRequest request, HttpServletResponse response,
			@RequestBody @Valid UserDTO user) {
		String userLogged = userService.login(request, response, user.getNickName(), user.getPassword());
		return ResponseEntity.ok(ResponseMessageVO.OK(userLogged + " successfully logged"));
	}
	
	@GetMapping("/logout")
	public Object logout(HttpServletRequest request, HttpServletResponse response) {
		userService.logout(request, response);
		return ResponseEntity.ok(ResponseMessageVO.OK("Logout succesful"));
	}
}
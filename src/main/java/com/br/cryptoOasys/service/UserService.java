package com.br.cryptoOasys.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cryptoOasys.exceptions.BadRequestException;
import com.br.cryptoOasys.exceptions.UserNotLoggedException;
import com.br.cryptoOasys.model.UserDTO;
import com.br.cryptoOasys.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public UserDTO register(HttpServletRequest request, String name, String nickName, String password) {		
		String errorMsg= "Error to register user";
		try {
			Optional<UserDTO> userExistent = userRepository.findByNickName(nickName);
			if(userExistent.isPresent()) {
				errorMsg = "User already exist";
				throw new BadRequestException(errorMsg);
			}else {
				UserDTO user = UserDTO.builder().
						name(name).
						nickName(nickName).
						password(password).build();	
				return userRepository.save(user);				
			}				
		}catch(Exception error) {
			throw new BadRequestException(errorMsg);
		}		
	}
	
	
	public String login(HttpServletRequest request, HttpServletResponse response,
			String nickName, String password) {
		Optional<UserDTO> user = userRepository.findByNickName(nickName);
		HttpSession session = request.getSession();
		if(user.isPresent()) {
			if(correctPassword(user, password)) {
				session.setAttribute("userLogged", nickName);
				return user.get().getName();
			}
			throw new BadRequestException("Incorrect password");
		}
		throw new BadRequestException("User not exist");	
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			session.setAttribute("userLogged", null);	
		}catch (Exception e) {
			throw new BadRequestException("Error to logou");
		}		
	}
	
	public void verifyIfUserIsLogged(HttpServletRequest request)  {		
		HttpSession session = request.getSession();		
		if(session.getAttribute("userLogged") == null) {
			throw new UserNotLoggedException("User not logged");
		}															
	}
	
	public String getLoggedUser(HttpServletRequest request) {
		this.verifyIfUserIsLogged(request);
		HttpSession session = request.getSession();
		return session.getAttribute("userLogged").toString();
	}
	
	public boolean correctPassword(Optional<UserDTO> user, String password) {
		return user.get().getPassword().equals(password) ? true : false;
	}
}

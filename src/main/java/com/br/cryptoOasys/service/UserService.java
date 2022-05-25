package com.br.cryptoOasys.service;

import java.util.Optional;

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
	
	@Autowired
	RedisService redisService;
	
	public UserDTO register(String name, String nickName, String password) {		
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
	
	
	public String login(String nickName, String password) {
		Optional<UserDTO> user = userRepository.findByNickName(nickName);
		if(user.isPresent()) {
			if(correctPassword(user, password)) {
				redisService.setUserLogged(user.get());		
				return user.get().getName();
			}
			throw new BadRequestException("Incorrect password");
		}
		throw new BadRequestException("User not exist");	
	}
	
	public void logout() {
		try {
			redisService.userLogout();
		}catch (Exception e) {
			throw new BadRequestException("Error to logout");
		}		
	}
	
	public void verifyIfUserIsLogged()  {		
		if(redisService.getUserLogged() == null) {
			throw new UserNotLoggedException("User not logged");
		}															
	}
	
	public boolean correctPassword(Optional<UserDTO> user, String password) {
		return user.get().getPassword().equals(password) ? true : false;
	}
}

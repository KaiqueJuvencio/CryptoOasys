package com.br.cryptoOasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cryptoOasys.exceptions.BadRequestException;
import com.br.cryptoOasys.model.UserDTO;
import com.br.cryptoOasys.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public UserDTO register(String name, String nickName, String password) {
		try {
			UserDTO user = UserDTO.builder().
					name(name).
					nickName(nickName).
					password(password).build();	
			return userRepository.save(user);	
		}catch(Exception error) {
			throw new BadRequestException("Error to register user");
		}		
	}
}

package com.br.cryptoOasys.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	
	public boolean login(HttpServletRequest request, HttpServletResponse response,
			String nickName, String password) {
		Optional<UserDTO> user = userRepository.findByNickName(nickName);
		HttpSession session = request.getSession();
		if(user.isPresent()) {
			if(user.get().getPassword().equals(password)) {
				session.setAttribute("userLogged", nickName);
				return true;
			}
			return false;
		}
		return false;		
	}
}

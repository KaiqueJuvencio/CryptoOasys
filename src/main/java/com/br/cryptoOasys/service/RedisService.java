package com.br.cryptoOasys.service;

import org.springframework.stereotype.Service;

import com.br.cryptoOasys.model.UserDTO;

import redis.clients.jedis.Jedis;

@Service
public class RedisService {
	
	Jedis jedis = new Jedis();
	
	public void setUserLogged(UserDTO user) {		
		jedis.set("userLoggedNick", user.getNickName());
		jedis.set("userLoggedName", user.getName());	
	}
	
	public String getUserLogged() {		
		return jedis.get("userLoggedNick");		
	}
	
	public void userLogout() {		
		jedis.del("userLoggedNick", "userLoggedName");		
	}
}

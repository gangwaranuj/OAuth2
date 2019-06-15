package com.example.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService  {

	public User doUserAuth(String login, String password) throws Exception{
		User user = null;

		if(login.equals("user1")) {
			user = new User();
			user.setUsername("test");
			Role r = new Role();
			r.setName("ROLE_ADMIN");
			List<Role> roles = new ArrayList<Role>();
			roles.add(r);
			user.setAuthorities(roles);
		}	
		if(user==null) {
			throw new Exception("Invalid credentials");
		}
		return user;
	}
}



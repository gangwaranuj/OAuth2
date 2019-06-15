package com.example.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider  implements AuthenticationProvider{

	
	@Autowired
    private UserService userService;
	
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		User user = null;
		try {
			user = (User) userService.doUserAuth(username,password);
		} catch (Exception e) {
			throw new AuthenticationCredentialsNotFoundException("login.error.incorrectpass");
		}
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.equals(
		          UsernamePasswordAuthenticationToken.class);
	}

}

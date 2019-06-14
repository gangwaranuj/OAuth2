package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


	@Autowired
	private TokenStore tokenStore;
	@Autowired
	private UserApprovalHandler userApprovalHandler;
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	private static String REALM="MY_OAUTH_REALM";
	private static final String CLIEN_ID = "my-trusted-client";
	private static final String GRANT_TYPE = "password";
	private static final String CLIENT_SECRET = "secret";
	private static final String AUTHORIZATION_CODE = "authorization_code";
	private static final String REFRESH_TOKEN = "refresh_token";
	private static final String IMPLICIT = "implicit";
	private static final String SCOPE_READ = "read";
	private static final String SCOPE_WRITE = "write";
	private static final String SCOPE_TRUST = "trust";
	private	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
	private	static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;

	
	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory() 
		.withClient(CLIEN_ID) 
		.secret(CLIENT_SECRET)
		.scopes(SCOPE_READ, SCOPE_WRITE,SCOPE_TRUST) 
		.authorities("USER", "ADMIN")
		.authorizedGrantTypes(GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
		.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).//Access token is only valid for 2 minutes.
		refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);//Refresh token is only valid for 10 minutes.
	}


	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
		.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.allowFormAuthenticationForClients();
		oauthServer.realm(REALM+"/client");
		
	}

}

package com.Blog_Application.Security;

import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

@Component
public class jwtTokenHelper {

	public  static final long jwt_token_validity = 5 * 60 * 60;
	
	private String secret = "jwtTokenKey";
	
	// retrive token from jwt
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token,Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token) {
	   return getClaimFromToken(token,Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token,Function<Claims,T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
}

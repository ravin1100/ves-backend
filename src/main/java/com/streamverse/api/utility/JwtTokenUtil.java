package com.streamverse.api.utility;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.streamverse.api.model.user.Role;
import com.streamverse.api.service.auth.impl.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
	
	@Value("${app.jwt.expirationInMs}")
	private int jwtExpirationInMs;

	@Value("${app.jwt.secret}")
	private String jwtsecret;

	public String getUserNameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		byte[] keyBytes = Base64.getDecoder().decode(jwtsecret);
		SecretKey key = Keys.hmacShaKeyFor(keyBytes); 
		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
	}

	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(String userName, Map<String, Object> map) {
		return doGenerateToken(map, userName);
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		byte[] keyBytes = Base64.getDecoder().decode(jwtsecret);
		Key key = Keys.hmacShaKeyFor(keyBytes);
		return Jwts.builder().claims(claims).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs)).subject(subject)
				.signWith(key).compact();
	}

	public Role getRoleFromJwtToken(String token) {
		Claims allClaimsFromToken = getAllClaimsFromToken(token);
		return Role.valueOf((String) allClaimsFromToken.get("ROLE"));
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUserNameFromToken(token);
		var userDetail = (UserDetailsImpl) userDetails;
		return (username.equals(userDetail.getEmail()) || username.equals(userDetail.getMobileNumber()))
				&& !isTokenExpired(token);
	}
	
//	public Boolean validateToken(String token, UserDetails userDetails) {
//		final String username = getUserNameFromToken(token);
//		boolean val = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//		return val;
//	}
	
}

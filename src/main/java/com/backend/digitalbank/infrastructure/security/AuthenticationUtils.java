package com.backend.digitalbank.infrastructure.security;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

@Component
public class AuthenticationUtils {
	@Value("${security.key}")
	private String secretKey;

	@Value("${security.bearer.token.time}")
	private long timeToExpireMillis;

	public String getJWTToken(String username) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("DIGITAL_BANK_JWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + timeToExpireMillis))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

	public String getSubjectToken(String token) {
		Claims claim = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token.replace("Bearer ", ""))
				.getBody();

		return claim.getSubject();
	}
}

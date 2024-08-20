package com.francoisgib.project_service.jwt;

import com.francoisgib.common.UserPrincipal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class JwtService {
	private static final long TO_SECONDS = 1000L;

	@Value("${jwt.cookieExpiry}")
	private Long cookieExpiry;

	@Value("${jwt.secretKey}")
	private String jwtSecret;

	public String generateToken(String username, UserPrincipal principal) {
		Map<String, Object> claims = Map.of(
				"organizationName", principal.organizationName(),
				"organizationId", principal.organizationId(),
				"userId", principal.userId()
		);
		return createToken(claims, username);
	}

	private String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder()
			.setIssuer("project-service")
			.setAudience("project-service")
			.setClaims(claims)
			.setSubject(username)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + cookieExpiry * TO_SECONDS))
			.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
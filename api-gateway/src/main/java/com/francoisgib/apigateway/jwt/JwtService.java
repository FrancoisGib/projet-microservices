package com.francoisgib.apigateway.jwt;

import com.francoisgib.common.UserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

@Slf4j
@Service
public class JwtService {
	@Value("${jwt.secretKey}")
	private String jwtSecret;

	public UserPrincipal extractPrincipal(String token) {
		return new UserPrincipal(
				extractPrincipal(token, "userId", Long.class),
				extractPrincipal(token, "organizationId", Integer.class),
				extractPrincipal(token, "organizationName", String.class));
	}

	public <T> T extractPrincipal(String token, String fieldName, Class<T> classType) {
		return extractClaim(token, (claimsResolver) -> claimsResolver.get(fieldName, classType));
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts
			.parserBuilder()
			.setSigningKey(getSignKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			log.error("Invalid JWT signature: {}", ex.getMessage());
		} catch (ExpiredJwtException ex) {
			log.info("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.info("Unsupported JWT token");
		} catch (MalformedJwtException ex) {
			log.info("Malformed JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
			log.info(ex.getMessage());
		}
		return false;
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
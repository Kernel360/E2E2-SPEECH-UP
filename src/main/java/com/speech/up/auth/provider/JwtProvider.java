package com.speech.up.auth.provider;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtProvider {
	@Value("${jwt.secret.key}")
	private String secretKey;

	public String createToken(String userId) {
		Date expirationDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
		Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

		return Jwts.builder()
			.signWith(key, SignatureAlgorithm.HS256)
			.setSubject(userId).setIssuedAt(new Date()).setExpiration(expirationDate).compact();
	}

	public String validate(String jwt) {
		String subject;
		Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

		try {
			subject = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(jwt)
				.getBody()
				.getSubject();
		} catch (NullPointerException nullPointerException) {
			log.error("Invalid JWT");
			throw new IllegalArgumentException("JwtProvider 클래스에 문제 있으니 확인해라.");
		}
		return subject;
	}

	public String getHeader(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");

		if (Objects.nonNull(authorization) && authorization.startsWith("Bearer ")) {
			authorization = authorization.substring(7);
		}

		return validate(authorization);
	}
}

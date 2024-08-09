package com.speech.up.oAuth.provider;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
	@Value("${jwt.secret.key}")
	private String secretKey;

	public String createToken(String userId) {
		Date expirationDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS)); // 현재부터 1시간 뒤까지
		Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)); // 발급받은 토큰 UTF-8 로 인코딩

		return Jwts.builder()
			.signWith(key, SignatureAlgorithm.HS256)
			.setSubject(userId).setIssuedAt(new Date()).setExpiration(expirationDate).compact();
	}

	public String validate(String jwt) {
		String subject = null;
		Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

		try {

			subject = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(jwt)
				.getBody()
				.getSubject();

		} catch (NullPointerException nullPointerException) {
			throw new IllegalArgumentException("JwtProvider 클래스에 문제 있으니 확인해라.");
		} catch (Exception exception) {
			throw new IllegalArgumentException(exception);
		}
		return subject;
	}
}

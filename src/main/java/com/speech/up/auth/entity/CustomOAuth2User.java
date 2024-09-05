package com.speech.up.auth.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CustomOAuth2User implements OAuth2User {

	private String userId;
	private String authorities; // 사용자 권한을 쉼표로 구분된 문자열로 저장

	@Override
	public Map<String, Object> getAttributes() {
		Map<String, Object> attributes = new HashMap<>();
		attributes.put("userId", userId);
		return attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Objects.isNull(authorities) ? parseAuthorities(authorities) : Set.of();
	}

	@Override
	public String getName() {
		return this.userId;
	}

	// 권한 문자열을 파싱하여 GrantedAuthority의 컬렉션으로 변환
	private Collection<? extends GrantedAuthority> parseAuthorities(String authorities) {
		return Arrays.stream(authorities.split(","))
			.map(String::trim)
			.filter(auth -> !auth.isEmpty())
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());
	}
}

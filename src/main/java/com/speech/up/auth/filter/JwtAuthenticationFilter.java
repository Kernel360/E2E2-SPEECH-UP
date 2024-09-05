package com.speech.up.auth.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.speech.up.auth.provider.JwtProvider;
import com.speech.up.common.enums.StatusCode;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

import jakarta.annotation.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;

	@Override
	protected void doFilterInternal(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response,
		@Nullable FilterChain filterChain) throws ServletException, IOException {
		try {
			assert Objects.nonNull(filterChain);
			assert Objects.nonNull(request);
			String token = parseBearerToken(request);
			if (token.equals(String.valueOf(StatusCode.NO_AUTHORIZATION))) {
				filterChain.doFilter(request, response);
				return;
			}
			String socialId = jwtProvider.validate(token);
			if (socialId == null) {
				filterChain.doFilter(request, response);
				return;
			}
			UserEntity userEntity = userRepository.findBySocialId(socialId)
				.orElseThrow(() -> new NoSuchElementException("not found UserEntity by socialId : " + socialId));
			;
			String role = userEntity.getAuthorization();
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(role));
			SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
			AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(socialId, token,
				authorities);

			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			securityContext.setAuthentication(authenticationToken);
			SecurityContextHolder.setContext(securityContext);

		} catch (Exception exception) {
			throw new IOException("JWT Authentication 이 실패 했으니 확인 : ", exception);
		}

		filterChain.doFilter(request, response);
	}

	private String parseBearerToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		boolean hasAuthorization = StringUtils.hasText(authorization);
		if (!hasAuthorization) {
			log.warn("Authorization header is empty");
			return String.valueOf(StatusCode.NO_AUTHORIZATION);
		}

		boolean isBearer = authorization.startsWith("Bearer ");
		if (!isBearer) {
			log.warn("Authorization header is invalid");
			return String.valueOf(StatusCode.NO_AUTHORIZATION);
		}

		return authorization.substring(7);
	}
}

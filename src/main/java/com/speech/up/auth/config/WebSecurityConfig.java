package com.speech.up.auth.config;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.speech.up.auth.filter.JwtAuthenticationFilter;
import com.speech.up.auth.handler.OAuth2SuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final DefaultOAuth2UserService oAuth2UserService;
	private final OAuth2SuccessHandler oAuth2SuccessHandler;

	@Value("${google.cloud.url}")
	private String allowUrl;

	@Bean
	public WebSecurityCustomizer configured() { // 스프링 시큐리티 기능 비활성화
		return (web) -> web.ignoring()
			.requestMatchers(
				new AntPathRequestMatcher("/images/**"),
				new AntPathRequestMatcher("/css/**"),
				new AntPathRequestMatcher("/js/**"),
				new AntPathRequestMatcher("/map")
			);
	}

	@Bean
	protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.csrf(CsrfConfigurer::disable)
			.httpBasic(HttpBasicConfigurer::disable)
			.sessionManagement(
				sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(request -> request.requestMatchers(
					"api/v1/auth/**", "/oauth2/**", "/sign-up", "/logout", "/login",
					"/boards", "/boards/**", "/api/upload",
					"/.well-known/**", "/api/open/data/*",
					"/report", "/scripts", "/script-write", "/script-list", "/replies/**",
					"/admin/view", "/page/me", "/speech-record", "reports/**", "/").permitAll()
				.requestMatchers("/api/boards").hasAnyRole("ADMIN_USER", "GENERAL_USER")
				.requestMatchers("/users/me").hasAnyRole("ADMIN_USER", "GENERAL_USER")
				.requestMatchers("/speech-record").hasAnyRole("ADMIN_USER", "GENERAL_USER")
				.requestMatchers("/speech-record/**").hasAnyRole("ADMIN_USER", "GENERAL_USER")
				.requestMatchers("/api/admin/user/all").hasRole("ADMIN_USER")
				.requestMatchers("/api/admin/me").hasRole("ADMIN_USER")
				.anyRequest().authenticated())
			.oauth2Login(oauth2 -> oauth2
				.authorizationEndpoint(endpoint -> endpoint.baseUri("/oauth2/authorization/**"))
				.redirectionEndpoint(endpoint -> endpoint.baseUri("/login/oauth2/code/**"))
				.userInfoEndpoint(endpoint -> endpoint.userService(oAuth2UserService))
				.successHandler(oAuth2SuccessHandler))
			.exceptionHandling(
				exceptionHandling -> exceptionHandling.authenticationEntryPoint(new FailedAuthenticationEntryPoint()))
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}

	@Bean
	protected CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList(allowUrl));
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.addAllowedHeader("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/api/v1/**", corsConfiguration);

		return source;
	}
}

class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws
		IOException, ServletException {
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.getWriter().write("{\"code\" : \"NP\", \"message\" : \"No Permission\"}");
	}
}

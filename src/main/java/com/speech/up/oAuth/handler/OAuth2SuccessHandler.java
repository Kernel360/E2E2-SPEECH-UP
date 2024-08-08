package com.speech.up.oAuth.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.speech.up.oAuth.entity.CustomOAuth2User;
import com.speech.up.oAuth.provider.JwtProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final JwtProvider jwtProvider;
	private final HttpSession httpSession;
	@Override
	public void onAuthenticationSuccess(
		HttpServletRequest request,
		HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
		String socialId = oAuth2User.getName();
		String token = jwtProvider.createToken(socialId);
		System.out.println(oAuth2User);
		System.out.println(token);
		httpSession.setAttribute("socialId", socialId);
		response.sendRedirect("http://localhost:8080/");
	}
}

package com.speech.up.oAuth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speech.up.oAuth.service.OAuthService;
import com.speech.up.oAuth.service.dto.SignInRequestDto;
import com.speech.up.oAuth.service.dto.SignInResponseDto;
import com.speech.up.oAuth.service.dto.SignUpRequestDto;
import com.speech.up.oAuth.service.dto.SignUpResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class OAuthController {
	private final OAuthService oAuthService;

	@PostMapping("/sign-up")
	public ResponseEntity<? super SignUpResponseDto> signUp(
		@RequestBody @Valid SignUpRequestDto signUpRequestDto
	){
		ResponseEntity<? super SignUpResponseDto> response = oAuthService.signUp(signUpRequestDto);
		return response;
	}

	@PostMapping("/sign-in")
	public ResponseEntity<? super SignInResponseDto> signIn(
		@RequestBody @Valid SignInRequestDto signInRequestDto
	) {
		ResponseEntity<? super SignInResponseDto> response = oAuthService.signIn(signInRequestDto);
		return response;
	}
}

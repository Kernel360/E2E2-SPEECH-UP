package com.speech.up.oAuth.service;

import org.springframework.http.ResponseEntity;

import com.speech.up.oAuth.service.dto.IdCheckRequestDto;
import com.speech.up.oAuth.service.dto.IdCheckResponseDto;
import com.speech.up.oAuth.service.dto.SignInRequestDto;
import com.speech.up.oAuth.service.dto.SignInResponseDto;
import com.speech.up.oAuth.service.dto.SignUpResponseDto;
import com.speech.up.oAuth.service.dto.SignUpRequestDto;

public interface OAuthService {
	ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
	ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
	ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
